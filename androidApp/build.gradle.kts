plugins {
    id("com.android.application")
    kotlin("android")
}

val compose_version = "1.0.3"

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.animation:animation:$compose_version")
    implementation("androidx.compose.ui:ui-tooling:$compose_version")
    implementation("androidx.compose.runtime:runtime-livedata:$compose_version")
    implementation("androidx.compose.material:material-icons-extended:$compose_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0-beta01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.2")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")
    implementation("dev.icerock.moko:mvvm:0.11.0")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "app.antran.pexels.android"
        minSdk = 28
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = compose_version
    }
}