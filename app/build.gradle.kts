// app/build.gradle.kts: aquí los plugins se APLICAN. Safe Args, siempre el último.
// Sin org.jetbrains.kotlin.android: desde AGP 9.0 el soporte de Kotlin va integrado.
plugins {
    alias(libs.plugins.android.application)
    id("androidx.navigation.safeargs.kotlin")
}
android {
    namespace = "com.example.roteiro"
    compileSdk = 37
    defaultConfig {
        applicationId = "com.example.roteiro"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}