plugins {
    id(Plugins.Module.library)
    kotlin(Plugins.Module.android)
    kotlin(Plugins.Module.kapt)
}

android {
    namespace = AppConfig.namespaceData
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

    buildFeatures {
        // Disable unused AGP features
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }
}

dependencies {
    implementation(project(Dependencies.Project.domain))

    //Implementation
    implementation(Dependencies.Coroutines.implementation)
    implementation(Dependencies.Kotlin.Ktor.implementation)
    implementation(Dependencies.Hilt.implementation)
    implementation(Dependencies.Paging.runtime)

    //Kapt
    kapt(Dependencies.Hilt.kapt)

    // Debug Implementation
    debugImplementation(Dependencies.Chucker.debugImplementation)

    // Release Implementation
    releaseImplementation(Dependencies.Chucker.releaseImplementation)
}