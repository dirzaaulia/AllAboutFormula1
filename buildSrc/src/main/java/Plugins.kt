object Plugins {
    object Module {
        const val app = "com.android.application"
        const val library = "com.android.library"
        const val android = "android"
        const val kapt = "kapt"
        const val kotlinParcelize = "kotlin-parcelize"
        const val hilt = "dagger.hilt.android.plugin"
        const val spotless = "com.diffplug.spotless"
        const val serialization = "plugin.serialization"
    }

    object Project {
        const val app = "com.android.application"
        const val android = "org.jetbrains.kotlin.android"
        const val kapt = "org.jetbrains.kotlin.kapt"
        const val kotlinParcelize = "org.jetbrains.kotlin.plugin.parcelize"
        const val hilt = "com.google.dagger.hilt.android"
        const val spotless = "com.diffplug.spotless"
        const val serialization = "org.jetbrains.kotlin.plugin.serialization"
    }
}