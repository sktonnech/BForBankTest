import org.gradle.kotlin.dsl.android

plugins {
    kotlin("kapt")
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.bforbank.bforbanktest.feature.pokemon.data"
    compileSdk = 34

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain(11)
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(project(":feature:pokemon:domain"))

    implementation(libs.androidx.core.ktx)

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)


    implementation(libs.kotlinx.coroutines.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)


    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}