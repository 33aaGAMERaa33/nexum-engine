plugins {
    id("java")
    id("java-library")
}

group = "io.nexum.engine"
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

/* =========================================================
 * TASK TIPADA E COMPATÍVEL COM CONFIGURATION CACHE
 * ========================================================= */
abstract class GenerateRunInfoTask : DefaultTask() {

    @Input
    abstract fun getIsRelease(): Property<Boolean>

    @Input
    abstract fun getVersionName(): Property<String>

    @Input
    @Optional
    abstract fun getApplicationPath(): Property<String>

    @OutputDirectory
    abstract fun getOutputDir(): DirectoryProperty

    @TaskAction
    fun generate() {
        val baseDir = getOutputDir().get().asFile.resolve("io/nexum")
        baseDir.mkdirs()

        baseDir.resolve("Version.java").writeText(
            """
            package io.nexum;
            public final class Version {
                public static final String VERSION = "${getVersionName().get()}";
            }
            """.trimIndent()
        )

        baseDir.resolve("RunInfo.java").writeText(
            """
            package io.nexum;
            public final class RunInfo {
                public static final boolean IS_RELEASE = ${getIsRelease().get()};
            }
            """.trimIndent()
        )

        baseDir.resolve("ApplicationFile.java").writeText(
            """
            package io.nexum;
            public final class ApplicationFile {
                public static final String APPLICATION_PATH = "${getApplicationPath().orNull ?: ""}";
            }
            """.trimIndent()
        )
    }
}

/* =========================================================
 * REGISTRO DA TASK
 * ========================================================= */

val generateRunInfo by tasks.registering(GenerateRunInfoTask::class) {

    val releaseFlag = providers
        .gradleProperty("release")
        .map(String::toBoolean)
        .orElse(false)

    val appPath = providers.gradleProperty(
        if (releaseFlag.get()) "release_file" else "debug_file"
    )

    getIsRelease().set(releaseFlag)
    getVersionName().set(project.version.toString())
    getApplicationPath().set(appPath)
    getOutputDir().set(layout.buildDirectory.dir("generated/sources/java"))
}

/* =========================================================
 * SOURCE SETS
 * ========================================================= */

sourceSets {
    main {
        java.srcDir(layout.buildDirectory.dir("generated/sources/java"))
        resources.srcDir(layout.buildDirectory.dir("dart"))
    }
}

/* =========================================================
 * VÍNCULO DAS TASKS
 * ========================================================= */

tasks.named<JavaCompile>("compileJava") {
    dependsOn(generateRunInfo)
}

tasks.named("build") {
    dependsOn(generateRunInfo)
}

/* =========================================================
 * TESTES
 * ========================================================= */

tasks.register<JavaExec>("runTest") {
    group = "verification"
    mainClass.set("io.nexum.TestMain")
    classpath = sourceSets["test"].runtimeClasspath
    dependsOn(tasks.testClasses)
}

tasks.test {
    useJUnitPlatform()
}
