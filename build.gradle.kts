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

tasks.register("updateReadme") {
    group = "documentation"
    description = "Updates the version in README.md to match the current project version."

    val readmeFile = rootProject.file("README.md")
    val currentVersion = project.property("lib.version").toString()
    val currentGroup = project.property("lib.group").toString()

    doLast {
        println("Starting README update...")

        if (!readmeFile.exists()) {
            println("Error: README.md not found at ${readmeFile.absolutePath}")
            return@doLast
        }

        val originalContent = readmeFile.readText(Charsets.UTF_16LE)

        val implementationPattern = Regex("""implementation\("\Q$currentGroup\E:compose-shimmer-skeleton:.*?"\)""")
        val implementationReplacement = """implementation("$currentGroup:compose-shimmer-skeleton:$currentVersion")"""

        val tomlPattern = Regex("""composeShimmerSkeleton\s*=\s*".*?"""")
        val tomlReplacement = """composeShimmerSkeleton = "$currentVersion""""

        val newContent = originalContent
            .replace(implementationPattern, implementationReplacement)
            .replace(tomlPattern, tomlReplacement)

        if (originalContent != newContent) {
            readmeFile.writeText(newContent, Charsets.UTF_16LE)
            println("Success! README.md updated to $currentVersion")
        } else {
            println("Already up to date with gradle.properties values.")
        }
    }
}


allprojects {
    tasks.matching { it.name == "build" || it.name == "publishToMavenCentral"}.configureEach {
        dependsOn(rootProject.tasks.named("updateReadme"))
    }
}
