rootProject.name = "compose-shimmer-skeleton-parent"

include(":compose-shimmer-skeleton")
project(":compose-shimmer-skeleton").projectDir = file("library")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

