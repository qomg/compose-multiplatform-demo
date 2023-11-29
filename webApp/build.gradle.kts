@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

//val copyJsResources = tasks.create("copyJsResourcesWorkaround", Copy::class.java) {
//    from(project(":composeApp").file("src/commonMain/resources"))
//    into("build/processedResources/js/main")
//}
//
//val copyWasmResources = tasks.create("copyWasmResourcesWorkaround", Copy::class.java) {
//    from(project(":composeApp").file("src/commonMain/resources"))
//    into("build/processedResources/wasmJs/main")
//}
//
//afterEvaluate {
//    project.tasks.getByName("jsProcessResources").finalizedBy(copyJsResources)
//    project.tasks.getByName("wasmJsProcessResources").finalizedBy(copyWasmResources)
//}

kotlin {
    js(IR) {
        moduleName = "ComposeMultiplatformDemo"
        browser {
            commonWebpackConfig {
                outputFileName = "ComposeMultiplatformDemo.js"
            }
        }
        binaries.executable()
    }

    wasmJs {
        moduleName = "ComposeMultiplatformDemo"
        browser {
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    // Uncomment and configure this if you want to open a browser different from the system default 
                    // open = mapOf(
                    //     "app" to mapOf(
                    //         "name" to "google chrome"
                    //     )
                    // )

                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.rootDir.path)
                        add(project.rootDir.path + "/composeApp/")
                        add(project.rootDir.path + "/nonAndroidMain/")
                        add(project.rootDir.path + "/webApp/")
                    }
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        val jsWasmMain by creating {
            dependencies {
                implementation(project(":composeApp"))
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
        val jsMain by getting {
            dependsOn(jsWasmMain)
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.js)
            }
        }
        val wasmJsMain by getting {
            dependsOn(jsWasmMain)
        }
    }
}

compose.experimental {
    web.application {}
}
