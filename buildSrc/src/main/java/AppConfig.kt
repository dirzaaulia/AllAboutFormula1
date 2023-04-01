object AppConfig {
    const val namespaceApp = "com.dirzaaulia.smartphonespec"
    const val namespaceData = "com.dirzaaulia.smartphonespec.data"
    const val namespaceDomain = "com.dirzaaulia.smartphonespec.domain"
    const val compileSdk = 33
    const val minSdk = 26
    const val targetSdk = 33
    const val compiledSdk = "android-33"
    const val versionCode = 1
    const val versionName = "1"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    object SigningConfigs {
        const val debug = "debug"
        const val release = "release"
    }

    object ProGuard {
        const val name = "proguard-android-optimize.txt"
        const val rules = "proguard-rules.pro"
    }

    object Kotlin {
        const val jvmVersion = 11
        const val jvmTarget = "11"

        var freeCompilerArgs =
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi" +
                    " -opt-in=kotlinx.coroutines.FlowPreview" +
                    " -opt-in=com.google.accompanist.pager.ExperimentalPagerApi" +
                    " -opt-in=androidx.compose.foundation.ExperimentalFoundationApi" +
                    " -opt-in=androidx.compose.material.ExperimentalMaterialApi" +
                    " -opt-in=androidx.compose.animation.ExperimentalAnimationApi" +
                    " -opt-in=androidx.compose.material3.ExperimentalMaterial3Api"

        const val spotlessTarget = "*.gradle.kts"
    }

    object KeyStore {
        const val password = "stravinsky9"
        const val alias = "keydirza"
    }

    object Task {
        const val clean = "clean"
    }
}