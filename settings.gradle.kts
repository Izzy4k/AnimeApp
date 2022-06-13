pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android")) {
                useModule("com.android.tools.build:gradle:7.0.4")
            }
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.5.31")
            }
            if (requested.id.id.startsWith("dagger.hilt.android")) {
                useModule("com.google.dagger:hilt-android-gradle-plugin:2.42")
            }
            if(requested.id.id.startsWith("androidx.navigation")){
                useModule("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
            }
        }
    }
}
rootProject.name = "AnimeApp"
include(":app")
include(":data")
include(":domain")
include(":core")
