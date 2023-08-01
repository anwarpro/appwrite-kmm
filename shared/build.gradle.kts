plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
//                api("io.appwrite:sdk-for-android:2.0.0")
                api(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
                api("com.squareup.okhttp3:okhttp")
                implementation("com.squareup.okhttp3:okhttp-urlconnection")
                implementation("com.squareup.okhttp3:logging-interceptor")
                implementation("com.google.code.gson:gson:2.9.0")

                implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
                implementation("androidx.lifecycle:lifecycle-common-java8:2.5.1")
                implementation("androidx.appcompat:appcompat:1.6.0")
                implementation("androidx.fragment:fragment-ktx:1.5.5")
                implementation("androidx.activity:activity-ktx:1.6.1")
                implementation("androidx.browser:browser:1.4.0")
            }
        }
    }
}

android {
    namespace = "com.helloanwar.appwritekmm"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}