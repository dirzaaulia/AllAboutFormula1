plugins {
    id(Plugins.Project.app) version Version.toolsBuildGradle apply false
    id(Plugins.Project.android) version Version.kotlinGradle apply false
    id(Plugins.Project.kapt) version Version.kotlinGradle apply false
    id(Plugins.Project.kotlinParcelize) version Version.kotlinGradle apply false
    id(Plugins.Project.hilt) version Version.hilt apply false
    id(Plugins.Project.spotless) version Version.spotless apply false
    id(Plugins.Project.serialization) version Version.serialization apply false
}

tasks.register(AppConfig.Task.clean, Delete::class){
    delete(rootProject.buildDir)
}