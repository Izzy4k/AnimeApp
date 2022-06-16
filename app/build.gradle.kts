plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("kapt")
}
repositories {
    google()
    mavenCentral()
    jcenter()
    maven {
        setUrl("https://jitpack.io")
    }

}
android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.androidJUnit)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation(Dependencies.Test.uiAutomator)
    //Coil
    implementation(Dependencies.Android.coil)

    //Glide
    implementation(Dependencies.Android.glide)

    //Hilt
    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)

    //Coroutines
    implementation(Dependencies.Coroutines.coreCoroutines)

    //KTX
    implementation(Dependencies.Lifecycle.lifecycleKtx)
    implementation(Dependencies.Lifecycle.lifecycleViewModel)
    implementation(Dependencies.Lifecycle.lifecycleFragment)

    //Nav component
    implementation(Dependencies.NavigationComponent.nav_fragment)
    implementation(Dependencies.NavigationComponent.nav_ui)
    //Paging v3
    implementation(Dependencies.Paging.paging_v3)

    //Youtube Player
    implementation(Dependencies.YoutubePlayer.core)
    implementation(Dependencies.YoutubePlayer.chromecast)

    //Slider
    implementation(Dependencies.Pager.slider)
    implementation(Dependencies.Pager.app_compat)


    //Scale Image
    implementation(Dependencies.Pager.scaleImage)
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":core"))

}