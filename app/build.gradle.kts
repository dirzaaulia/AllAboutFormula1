/*
 * Copyright 2023 Dirza Aulia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.dirzaaulia.smartphonespec.SmartphoneSpecBuildType

plugins {
    id("dirzaaulia.smartphonespec.application")
    id("dirzaaulia.smartphonespec.application.compose")
    id("dirzaaulia.smartphonespec.hilt")
}

android {
    defaultConfig {
        applicationId = "com.dirzaaulia.smartphonespec"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = SmartphoneSpecBuildType.DEBUG.applicationIdSuffix
        }
        val release by getting {
            isMinifyEnabled = true
            applicationIdSuffix = SmartphoneSpecBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    namespace = "com.dirzaaulia.smartphonespec"
}

dependencies {

    /**
     * Project
     */
    implementation(project(":feature:latest"))
    implementation(project(":feature:search"))

    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))

    /**
     * Android Test
     */
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(kotlin("test"))

    /**
     * Debug Implementation
     */
    debugImplementation(libs.androidx.compose.ui.testManifest)

    /**
     * Implementation
     */
    //Accompanist
    implementation(libs.accompanist.systemuicontroller)
    //AndroidX
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    //Coil
    implementation(libs.coil.kt)
    //Material
    implementation(libs.material)
    //Timber
    implementation(libs.timber)
}