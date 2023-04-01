plugins {
    id(Plugins.Module.app)
    kotlin(Plugins.Module.android)
    kotlin(Plugins.Module.kapt)
    id(Plugins.Module.kotlinParcelize)
    id(Plugins.Module.hilt)
    id(Plugins.Module.spotless)
}

android {
    namespace = AppConfig.namespaceApp
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.testInstrumentationRunner
    }

    signingConfigs {
        getByName(AppConfig.SigningConfigs.debug) {
//            storeFile = file("D:\\AndroidStudio\\Keystore\\keystore.jks")
//            storePassword = AppConfig.KeyStore.password
//            keyAlias = AppConfig.KeyStore.alias
//            keyPassword = AppConfig.KeyStore.password
        }
        create(AppConfig.SigningConfigs.release) {
//            storeFile = file("D:\\AndroidStudio\\Keystore\\keystore.jks")
//            storePassword = AppConfig.KeyStore.password
//            keyAlias = AppConfig.KeyStore.alias
//            keyPassword = AppConfig.KeyStore.password
        }
    }

    buildTypes {
        getByName(AppConfig.SigningConfigs.debug) {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile(AppConfig.ProGuard.name),
                AppConfig.ProGuard.rules,
            )
            signingConfig = signingConfigs.getByName(AppConfig.SigningConfigs.debug)
        }
        getByName(AppConfig.SigningConfigs.release) {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile(AppConfig.ProGuard.name),
                AppConfig.ProGuard.rules,
            )
            signingConfig = signingConfigs.getByName(AppConfig.SigningConfigs.release)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain {
            this.languageVersion.set(JavaLanguageVersion.of(AppConfig.Kotlin.jvmVersion))
        }
    }

    kotlinOptions {
        jvmTarget = AppConfig.Kotlin.jvmTarget

        freeCompilerArgs = freeCompilerArgs + listOf(
            // Avoid having to stutter experimental annotations all over the codebase
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        )
    }

    buildFeatures {
        compose = true
        // Disable unused AGP features
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.composeCompiler
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Module
    implementation(project(Dependencies.Project.data))
    implementation(project(Dependencies.Project.domain))

    // Implementation
    implementation(Dependencies.Accompanist.implementation)
    implementation(Dependencies.AndroidX.implementation)
    implementation(Dependencies.AndroidX.Compose.implementation)
    implementation(Dependencies.AndroidX.Lifecycle.implementation)
    implementation(Dependencies.Coil.implementation)
    implementation(Dependencies.Coroutines.implementation)
    implementation(Dependencies.Hilt.implementation)
    implementation(Dependencies.Kotlin.implementation)
    implementation(Dependencies.Kotlin.Ktor.implementation)
    implementation(Dependencies.Material.implementation)
    implementation(Dependencies.Other.implementation)
    implementation(Dependencies.Paging.implementation)

    // Kapt
    kapt(Dependencies.Hilt.kapt)

    // Debug Implementation
    debugImplementation(Dependencies.Chucker.debugImplementation)

    // Release Implementation
    releaseImplementation(Dependencies.Chucker.releaseImplementation)

    // Android Test Implementation
    androidTestImplementation(Dependencies.AndroidX.Test.androidTestImplementation)
    androidTestImplementation(Dependencies.JUnit.androidTestImplementation)
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        ktlint()
    }
    kotlinGradle {
        target(AppConfig.Kotlin.spotlessTarget)
        ktlint()
    }
}
