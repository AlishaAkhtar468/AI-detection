plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.resconapss.documents.scanner.pdfgenerator"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.resconapss.documents.scanner.pdfgenerator"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    // For Lottie animations
    implementation ("com.airbnb.android:lottie-compose:6.4.0" )
    implementation(libs.androidx.navigation.compose)
    //navigation
    implementation("androidx.navigation:navigation-compose:2.7.3")


    //pager
   // implementation ("com.google.accompanist:accompanist-pager:0.12.0")
    implementation ("com.google.accompanist:accompanist-pager:0.28.0")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.28.0")
    implementation("io.coil-kt:coil-compose:2.2.0")


    // CameraX
    implementation ("androidx.camera:camera-camera2:1.3.2")
    implementation ("androidx.camera:camera-lifecycle:1.3.2")
    implementation ("androidx.camera:camera-view:1.3.2")

    implementation ("androidx.camera:camera-camera2:1.1.0-alpha04")
    implementation ("androidx.camera:camera-lifecycle:1.1.0-alpha04")
    implementation ("androidx.camera:camera-view:1.0.0-alpha34")
    implementation(libs.androidx.camera.lifecycle)

        implementation ("androidx.compose.ui:ui:1.4.0")
        implementation ("androidx.compose.material3:material3:1.0.0")
        implementation ("androidx.compose.ui:ui-tooling-preview:1.4.0")
        implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
        implementation ("androidx.activity:activity-compose:1.4.0")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")
    implementation ("androidx.compose.ui:ui:1.0.5")
    implementation ("androidx.compose.runtime:runtime-livedata:1.0.5")
    implementation ("org.tensorflow:tensorflow-lite-metadata:0.1.0")

    //TENSORFLOW lite
    implementation ("org.tensorflow:tensorflow-lite-support:0.3.0")
    implementation ("org.tensorflow:tensorflow-lite-task-vision:0.4.0")
    // Import the GPU delegate plugin Library for GPU inference
    implementation ("org.tensorflow:tensorflow-lite-gpu-delegate-plugin:0.4.0")
    implementation ("org.tensorflow:tensorflow-lite-gpu:2.9.0")
}