rootProject.name = "appp1"

pluginManagement {
    plugins {
        id("dev.detekt") version "2.0.0-alpha.6"
    }
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        mavenLocal() // Esencial para que Gradle encuentre el Design System publicado en local
    }
}

include(":androidApp")
include(":shared")