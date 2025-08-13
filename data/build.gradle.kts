plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}
android {
    namespace = "com.pt.data"
    compileSdk = 35
    defaultConfig { minSdk = 26 }
}
dependencies {
    implementation(project(":domain"))
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
}
