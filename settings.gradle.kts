pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
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
rootProject.name = "All About Formula 1"
include(":app")
include(":core:designsystem")
include(":core:data")
include(":feature:latest")
include(":core:network")
include(":core:model")
include(":core:common")
include(":core:domain")
include(":core:ui")
include(":feature:interest")
include(":feature:search")
include(":core:utilities")
include(":feature:list")
