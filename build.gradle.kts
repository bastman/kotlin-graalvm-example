import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Gradle built-in
    java
    kotlin("jvm") version "1.2.51"
    application

    // From the Gradle Plugin Portal
    id("com.bmuschko.docker-java-application") version "3.1.0"
    id("com.github.johnrengelman.plugin-shadow") version "2.0.3"
}

group = "com.example"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "com.example.demo.App"
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("com.google.guava:guava:23.0")

    // Use JUnit test framework
    testCompile("junit:junit:4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
