import java.util.Properties

val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) {
        file.inputStream().use { load(it) }
    }
}

val newsApiKey = localProperties.getProperty("NEWS_API_KEY") ?: ""

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.zonnon.newsapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.zonnon.newsapp"
        minSdk = 31
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "NEWS_API_KEY",
            "\"$newsApiKey\""
        )
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Splash Api
    implementation(libs.androidx.core.splashscreen)

    //Compose Navigation
    implementation(libs.androidx.navigation.compose)

    //Dagger Hilt
    implementation(libs.com.google.dagger.hilt)
    ksp(libs.com.google.dagger.hilt.compiler)
    implementation(libs.androidx.hilt.navigation)

    //Retrofit
    implementation(libs.com.squareup.retrofit2)
    implementation(libs.com.squareup.retrofit2.converter.gson)

    //Coil
    implementation(libs.io.coil.kt)

    //Datastore
    implementation(libs.androidx.datastore.preferences)

    //Paging 3
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
}