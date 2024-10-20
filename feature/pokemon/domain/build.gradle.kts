plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}


dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation (libs.javax.inject)
    testImplementation(libs.junit)
    testImplementation(libs.test.kotlinx.coroutines.test)
    testImplementation(libs.test.mockk)
    testImplementation(libs.test.turbine)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    jvmToolchain(11)
}