plugins {
    id("dagger.hilt.android.plugin")
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}
repositories {
    google()
    mavenCentral()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.androidJUnit)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation(Dependencies.Test.uiAutomator)

    implementation(project(":domain"))
    implementation(project(":core"))
    //Room
    implementation(Dependencies.Room.ktx)
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.paging)
    kapt(Dependencies.Room.compiler)

    //Hilt
    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)

    //Coroutines
    implementation(Dependencies.Coroutines.coreCoroutines)

    //KTX
    implementation(Dependencies.Lifecycle.lifecycleKtx)
    implementation(Dependencies.Lifecycle.lifecycleViewModel)
    implementation(Dependencies.Lifecycle.lifecycleFragment)

    //Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.convertor_gson)

    //Interceptor
    implementation(Dependencies.Interceptor.interceptor)

    //OkHttp
    implementation(Dependencies.OkHttpClient.okhttp)
    implementation(Dependencies.OkHttpClient.okhttp_bom)
    implementation(Dependencies.OkHttpClient.logging_interceptor)
    implementation(Dependencies.OkHttpClient.legacy_support)

    //Paging v3
    implementation(Dependencies.Paging.paging_v3)

}
