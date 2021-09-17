plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":comics"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    //Coroutines
    /*val coroutinesVersion = properties["version.kotlinx.coroutines"]
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")*/

    //Navigation
    val navVersion = properties["version.navigation"]
    implementation ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")

    //ViewModel
    /*val lifecycleVersion = properties["version.lifecycle"]
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")*/
    implementation("dev.icerock.moko:mvvm:0.11.0")

    // RecyclerView
    val recyclerviewVersion = properties["version.recyclerview"]
    implementation ("androidx.recyclerview:recyclerview:$recyclerviewVersion")

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    // MULTI DEX
    implementation ("androidx.multidex:multidex:2.0.0")

}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.droal.marvel.android"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}