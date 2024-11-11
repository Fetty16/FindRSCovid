plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.findrs"
    compileSdk = 34



    defaultConfig {
        applicationId = "com.example.findrs"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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

    viewBinding {

    }

    buildFeatures {
        dataBinding = true
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

    implementation(libs.appcompatLib)
    implementation(libs.materialLib)
    implementation(libs.activityLib)
    implementation(libs.constraintlayoutLib)
    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Dependensi tambahan menggunakan libs
    implementation(libs.kotlinStdlib)
    implementation(libs.cardview)
    implementation(libs.legacySupport)
    implementation(libs.recyclerview)
    implementation(libs.lifecycleLiveData)
    implementation(libs.lifecycleViewModel)
    implementation(libs.playServicesMaps)
    implementation(libs.playServicesLocation)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverterGson)
    implementation(libs.okhttpLogging)

    // Dependensi untuk testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)




}