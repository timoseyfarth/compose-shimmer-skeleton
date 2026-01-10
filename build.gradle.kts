plugins {
    kotlin("jvm") version "2.2.20"
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.vanniktechMavenPublish) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}


//plugins {
//    kotlin("jvm") version "2.2.21"
//}
//
//group = "dev.seyfarth"
//version = "1.0-SNAPSHOT"
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    testImplementation(kotlin("test"))
//}
//
//kotlin {
//    jvmToolchain(21)
//}
//
//tasks.test {
//    useJUnitPlatform()
//}
