plugins {
    id("java")
    id("java-library")
}

group = "io.nexum"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(17)
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.1.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

val generateRunInfo by tasks.registering {
    val isRelease = findProperty("release")?.toString()?.toBoolean() ?: false
    val outDir = layout.buildDirectory.dir("generated/sources/java")
    val applicationPath = findProperty(if (isRelease) "release_file" else "debug_file")

    inputs.property("is_release", isRelease)
    inputs.property("version", project.version)
    outputs.dir(outDir)

    doLast {
        val verFile = outDir.get().file("io/nexum/Version.java").asFile
        verFile.parentFile.mkdirs()
        verFile.writeText("""
            package io.nexum;
            public final class Version {
                public static final String VERSION = "${project.version}";
            }
        """.trimIndent())

        val infoFile = outDir.get().file("io/nexum/RunInfo.java").asFile
        infoFile.writeText("""
            package io.nexum;
            public final class RunInfo {
                public static final boolean IS_RELEASE = $isRelease;
            }
        """.trimIndent())

        val appFile = outDir.get().file("io/nexum/ApplicationFile.java").asFile
        appFile.writeText("""
            package io.nexum;
            public final class ApplicationFile {
                public static final String APPLICATION_PATH = "$applicationPath";
            }
        """.trimIndent())
    }
}

// --- CONFIGURAÇÃO DE DIRETÓRIOS ---
sourceSets {
    main {
        // Adiciona as pastas geradas ao compilador Java
        java.srcDir(layout.buildDirectory.dir("generated/sources/java"))
        // Adiciona o binário do Dart como um recurso (Resource) para ser lido pelo Java
        resources.srcDir(layout.buildDirectory.dir("dart"))
    }
}

// --- VÍNCULO DE TASKS ---
tasks.named("compileJava") {
    dependsOn(generateRunInfo)
}

tasks.named("build") {
    dependsOn(generateRunInfo)
}

// --- TASK DE TESTE ---
tasks.register<JavaExec>("runTest") {
    group = "verification"
    mainClass.set("io.nexum.TestMain")
    classpath = sourceSets["test"].runtimeClasspath
    dependsOn(tasks.testClasses)
}

tasks.test {
    useJUnitPlatform()
}