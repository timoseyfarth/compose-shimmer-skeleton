@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("maven-publish")
    alias(libs.plugins.vanniktechMavenPublish)
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    jvm("desktop")

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    wasmJs {
        browser()
    }
    js(IR) {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
        }
    }
}

android {
    namespace = "dev.seyfarth.composeshimmer"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

mavenPublishing {
    val currentVersion = project.property("lib.version").toString()
    val currentGroup = project.property("lib.group").toString()

    publishToMavenCentral()

    signAllPublications()

    coordinates(currentGroup, "compose-shimmer-skeleton", currentVersion)

    pom {
        name.set("Compose Shimmer Skeleton")
        description.set("Shimmer effect modifier and predefined skeleton loaders for Jetpack Compose and Kotlin Multiplatform")
        inceptionYear.set("2025")
        url.set("https://github.com/timoseyfarth/compose-shimmer-skeleton")
        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("timoseyfarth")
                name.set("Timo Seyfarth")
                email.set("timo@seyfarth.dev")
                url.set("https://timo.seyfarth.dev")
            }
        }
        scm {
            url.set("https://github.com/timoseyfarth/compose-shimmer-skeleton")
            connection.set("scm:git:git://github.com/timoseyfarth/compose-shimmer-skeleton.git")
            developerConnection.set("scm:git:ssh://github.com/timoseyfarth/compose-shimmer-skeleton.git")
        }
    }
}
