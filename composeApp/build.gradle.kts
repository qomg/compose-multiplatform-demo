@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
}


kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    js(IR) {
        browser()
    }

    wasmJs {
        browser()
    }
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.util)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.common)
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.cio)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.materialIconsExtended)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.material3)
            // TODO components which support kotlin multiplatform
//            implementation(libs.compose.ui.util)
            implementation(libs.kotlinx.datetime)
//            implementation(libs.coil.kt.compose)
//            implementation("org.jetbrains.compose.ui:ui-util")
        }
//        val iosMain by getting {
//            dependencies {
//                implementation(libs.ktor.client.darwin")
//            }
//        }
//        val iosSimulatorArm64Main by getting {
//            dependsOn(iosMain.get())
//        }
        val jsWasmMain by creating {
            dependsOn(commonMain.get())
        }
        val jsMain by getting {
            dependsOn(jsWasmMain)
            dependencies {
                implementation(libs.ktor.client.core)
            }
        }
        val wasmJsMain by getting {
            dependsOn(jsWasmMain)
        }
    }
}

android {
    namespace = "org.example.cmd"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.example.cmd"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            //proguard
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
        implementation(libs.compose.foundation.layout)
        implementation(libs.compose.ui.tooling.preview)
        implementation(libs.androidx.appcompat)
        implementation(libs.androidx.core.ktx)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.example.cmd"
            packageVersion = "1.0.0"
        }
    }
}