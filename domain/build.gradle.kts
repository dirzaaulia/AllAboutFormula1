plugins {
    id(Plugins.Module.library)
    kotlin(Plugins.Module.android)
    kotlin(Plugins.Module.kapt)
    kotlin(Plugins.Module.serialization)
}

android {
    namespace = AppConfig.namespace
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(AppConfig.ProGuard.name),
                AppConfig.ProGuard.rules
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = AppConfig.Kotlin.jvmTarget
    }
}

dependencies {

    implementation(Dependencies.Hilt.implementation)
    implementation(Dependencies.Kotlin.Ktor.serialization)
    implementation(Dependencies.Paging.runtime)

    kapt(Dependencies.Hilt.kapt)
}