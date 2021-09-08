import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {
        val ktorVersion = "1.6.1"
        val serializationVersion = "1.2.2"
        val coroutinesVersion = properties["version.kotlinx.coroutines"]
        val sqlDelightVersion: String by project
        val kodeinVersion = "7.1.0"
        val mokomvvmVersion = "0.10.0"

        val commonMain by getting {
            dependencies {
                // KTOR
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                // COROUTINES
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                //SERIALIZATION
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                // SQL Delight
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
                // MOKO - MVVM
                implementation ("dev.icerock.moko:mvvm:$mokomvvmVersion")
                // KODE IN
                implementation ("org.kodein.di:kodein-di:$kodeinVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                // KTOR
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                // SQL Delight
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
                // MOKO - MVVM
                //implementation ("androidx.lifecycle:lifecycle-extensions:$androidxLifecycleVersion")
            }
        }
        val androidAndroidTestRelease by getting
        val androidTest by getting {
            dependsOn(androidAndroidTestRelease)
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
    }
}

sqldelight {
    database("MarvelDatabase") {
        packageName = "droal.shareddb"
    }
}