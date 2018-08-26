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
    val jacksonVersion = "2.9.6"

    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))
    //compile("org.jetbrains.kotlin:kotlin-reflect")
    //compile("com.google.guava:guava:23.0")
    /*
    compile("org.yaml:snakeyaml:1.21")
    compile("com.xenomachina:kotlin-argparser:2.0.7")


*/
    compile( "com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    compile( "com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    compile( "com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    compile( "com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")
    compile( "com.fasterxml.jackson.module:jackson-modules-java8:$jacksonVersion")

    compile( "com.fasterxml.jackson.module:jackson-module-parameter-names:$jacksonVersion")
    compile( "com.fasterxml.jackson.datatype:jackson-datatype-jdk8:$jacksonVersion")
    compile( "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    compile("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")

    // Use JUnit test framework
    testCompile("junit:junit:4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=enable")
    }
}
