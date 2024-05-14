plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.gokhanakbas.isyeriegitimiandroidapp"
    compileSdk = 34

    androidResources {
        generateLocaleConfig = true    }

    defaultConfig {
        applicationId = "com.gokhanakbas.isyeriegitimiandroidapp"
        minSdk = 29
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
    //Splash Api
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.gson)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Arrow - Asenkronize işleri yürütmek için kullanılan bir kütüphane
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)

    //Coil  - Fotoğraf Yüklemek İçin
    implementation(libs.coil.compose)

    //DataStore   - Localde veri depolamak için
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences)

    //dagger hilt  - Bağımlılık Enejksiyonu için
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //PostgreSQL
    implementation(libs.postgresql)
    //implementation("postgresql:postgresql:9.1-901-1.jdbc4")
}