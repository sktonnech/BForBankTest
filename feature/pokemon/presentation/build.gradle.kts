import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.kotlin.compose)

    id("kotlin-kapt")
    id("kotlin-parcelize")
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.bforbank.bforbanktest.feature.pokemon.presentation"
    compileSdk = 34

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain(11)
    }
    composeCompiler {
        featureFlags.addAll(ComposeFeatureFlag.StrongSkipping)
    }
}

dependencies {

    implementation(project(":feature:pokemon:domain"))
    implementation(project(":core:design-system"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)


    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.material)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.collections.immutable)


    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose.android)

    // Lifecycle ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.android)


    testImplementation(libs.junit)
    testImplementation(libs.test.kotlinx.coroutines.test)
    testImplementation(libs.test.mockk)
    testImplementation(libs.test.turbine)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    debugImplementation(libs.ui.tooling)
    implementation(libs.ui.tooling.preview)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}