val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "2.2.0"
    //id("io.ktor.plugin") version "3.2.3"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.todo2"
version = "0.0.1"

//application {
//    mainClass = "io.ktor.server.netty.EngineMain"
//    mainClass.set("com.todo2.ApplicationKt") // Replace with your actual main class
//}
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}


repositories {
    mavenCentral()
}
dependencies {
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
    implementation("io.ktor:ktor-server-core:2.3.7")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-server-netty:2.3.7")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml:2.3.7")
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    manifest {
        attributes(mapOf("Main-Class" to "com.todo2.ApplicationKt"))
    }
}
