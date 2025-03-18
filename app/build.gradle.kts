import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "2.0.21"
}

// local.properties 안의 내용 읽기
val localProperties: Properties by lazy {
    Properties().apply {
        rootProject.file("local.properties")
            .takeIf { it.exists() }?.inputStream()?.use { load(it) }
    }
}

android {
    namespace = "com.jeong.sesac.chaksaicompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jeong.sesac.chaksaicompose"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "KAKAO_API_KEY",
            "\"${localProperties.getProperty("KAKAO_API_KEY", "")}\""
        )

        buildConfigField(
            "String",
            "KAKAO_REST_API_KEY",
            "\"${localProperties.getProperty("KAKAO_REST_API_KEY", "")}\""
        )

        manifestPlaceholders["KAKAO_API_KEY"] =
            localProperties.getProperty("KAKAO_API_KEY", "")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.preference.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.play.services.location)
    implementation(libs.places)
    implementation(libs.ru.ldralighieri.corbind)
    implementation(libs.corbind.appcompat)
    implementation(libs.corbind.activity)

    implementation(project(":data"))
    implementation(project(":feature"))

    // preference
    implementation(libs.datastore.preference)

    // retrofit + moshi
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi)
    implementation(libs.converter.moshi)
    implementation(libs.retrofit)

    // kakao
    implementation (libs.kakao.maps)
    implementation (libs.kakao.sdk)

    // shared preference
    implementation(libs.androidx.preference.ktx)

    // tedPermission
    implementation(libs.tedpermission.normal)

    // Moshi 관련
    kapt(libs.moshi.kotlin.codegen)

    // coil, coil gif
    implementation(libs.coil3.coil.compose)
    implementation(libs.coil.gif)
    implementation(libs.coil.network.okhttp)

    // cameraX 관련 dependencies
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.extensions)
    implementation(libs.guava)

    // MLKit barcode
    implementation(libs.barcode.scanning)

    // serialize
    implementation(libs.kotlinx.serialization.json)


}