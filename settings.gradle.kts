pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BforBank"
include(":app")
include(":app:domain")
include(":app:data")
include(":app:presentation")

include(":core")
include(":core:design-system")

include(":feature")
include(":feature:pokemon")
include(":feature:pokemon:domain")
include(":feature:pokemon:data")
include(":feature:pokemon:presentation")