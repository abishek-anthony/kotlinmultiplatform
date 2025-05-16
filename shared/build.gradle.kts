import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    /*TODO: Add in the libs*/
    kotlin("plugin.serialization") version "1.9.10"
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    mavenLocal()
    google()
    // Required for Kotlin-wrappers (like org.w3c.notifications)
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")

    // For Kotlin JS/WRAPPER libraries (like kotlin-notifications)
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-wrappers/maven")
}

kotlin {
    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio) // For JVM
            implementation(libs.kotlinx.coroutines.core)
            /*TODO: Add in the libs*/
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
        }
        wasmJsMain.dependencies {
            implementation(libs.ktor.client.js) // For Web

            // Notification wrapper (if needed)
            implementation("org.jetbrains.kotlin-wrappers:kotlin-notifications:1.0.0-pre.697")
        }


    }
}

