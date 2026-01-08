plugins {
    id("java")
    id("java-library")
}

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

val coreBuildDir = layout.buildDirectory
val dartOutput = coreBuildDir.file("dart/${findProperty("dart_output")}")
val dartMain = file("${rootDir}/${findProperty("dart_main_dir")}/${findProperty("dart_main")}")

val compileDart by tasks.register<Exec>("compileDart") {
    group = "build"
    description = "Compila o código Dart para um executável nativo."

    doFirst {
        dartOutput.get().asFile.parentFile.mkdirs()
    }

    commandLine(
        "dart",
        "compile",
        "exe",
        dartMain.absolutePath,
        "-o",
        dartOutput.get().asFile.absolutePath
    )

    inputs.file(dartMain)
    outputs.file(dartOutput)
}

compileDart.onlyIf {
    gradle.taskGraph.hasTask(":build") ||
            gradle.taskGraph.hasTask(":assemble")
}

val generateVersion by tasks.registering {
    val outDir = layout.buildDirectory.dir("generated/version")

    inputs.property("version", project.version)
    outputs.dir(outDir)

    doLast {
        val file = outDir.get().file("Version.java").asFile
        file.parentFile.mkdirs()
        file.writeText(
            """
            package io.nexum;

            public final class Version {
                public static final String VERSION = "${project.version}";
            }
            """.trimIndent()
        )
    }
}

val generateRunInfo by tasks.registering {
    val isRelease = gradle.startParameter.taskNames.any {
        it in listOf("build", "assemble", "jar") || it.endsWith(":build")
    }

    val outDir = layout.buildDirectory.dir("generated/runInfo")

    inputs.property("is_release", isRelease)
    outputs.dir(outDir)

    doLast {
        val file = outDir.get().file("RunInfo.java").asFile
        file.parentFile.mkdirs()
        file.writeText(
            """
            package io.nexum;

            public final class RunInfo {
                public static final boolean IS_RELEASE = $isRelease;
            }
            """.trimIndent()
        )
    }
}

sourceSets["main"].java.srcDirs(
    layout.buildDirectory.dir("generated/version"),
    layout.buildDirectory.dir("generated/runInfo")
)
sourceSets["test"].java.srcDirs(
    layout.buildDirectory.dir("generated/version"),
    layout.buildDirectory.dir("generated/runInfo")
)

sourceSets {
    named("main") {
        resources.srcDir(layout.buildDirectory.dir("dart"))
    }
}

tasks.named<Copy>("processResources") {
    dependsOn(compileDart)

    from(dartOutput) {
        into("dart")
    }
}

tasks.named("build") {
    dependsOn(compileDart)
}

tasks.named("compileJava") {
    dependsOn(generateVersion, generateRunInfo)
}

tasks.named("compileTestJava") {
    dependsOn(generateVersion, generateRunInfo)
}

tasks.register<JavaExec>("runTest") {
    group = "verification"
    mainClass.set("io.nexum.TestMain")

    // IMPORTANTE: Agora usamos o classpath de 'test' em vez de 'main'
    classpath = sourceSets["test"].runtimeClasspath

    // Garante que os testes são compilados antes de correr
    dependsOn(tasks.testClasses)
}