import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    extra.apply {
    }
}

plugins {
    val kotlinVersion = "1.9.24"

    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("kapt") version kotlinVersion

    idea
}

group = "com.goofy"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}

dependencies {
    /** spring starter */
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    /** kotlin */
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    /** logger */
    implementation("io.github.oshai:kotlin-logging-jvm:${DependencyVersion.KOTLIN_LOGGING}")
    implementation("net.logstash.logback:logstash-logback-encoder:${DependencyVersion.LOGBACK_ENCODER}")

    /** swagger */
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${DependencyVersion.SPRINGDOC}")
    runtimeOnly("com.github.therapi:therapi-runtime-javadoc-scribe:${DependencyVersion.JAVADOC_SCRIBE}")
    kapt("com.github.therapi:therapi-runtime-javadoc-scribe:${DependencyVersion.JAVADOC_SCRIBE}")

    /** database */
    runtimeOnly("io.asyncer:r2dbc-mysql")
    runtimeOnly("com.mysql:mysql-connector-j")

    /** etc */
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    val isMacOS = System.getProperty("os.name").startsWith("Mac OS X")
    val architecture = System.getProperty("os.arch").lowercase()
    if (isMacOS && architecture == "aarch64") {
        developmentOnly("io.netty:netty-resolver-dns-native-macos:${DependencyVersion.MAC_DNS}:osx-aarch_64")
        testImplementation("io.netty:netty-resolver-dns-native-macos:${DependencyVersion.MAC_DNS}:osx-aarch_64")
    }
}

object DependencyVersion {
    /** log */
    const val KOTLIN_LOGGING = "6.0.9"
    const val LOGBACK_ENCODER = "7.4"

    /** springdoc */
    const val SPRINGDOC = "2.3.0"
    const val JAVADOC_SCRIBE = "0.15.0"

    /** external */
    const val MAC_DNS = "4.1.111.Final"
}

defaultTasks("bootRun")

springBoot.buildInfo { properties { } }

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "8.11.1"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
