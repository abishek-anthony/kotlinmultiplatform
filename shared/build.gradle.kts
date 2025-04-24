import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinMokkery)
}

repositories {
    mavenCentral()
    mavenLocal()
    google()
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
        all {
            languageSettings.optIn("dev.mokkery.annotations.DelicateMokkeryApi")
            languageSettings.optIn("dev.mokkery.annotations.InternalMokkeryApi")
        }
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio) // For JVM
        }
        wasmJsMain.dependencies {
            implementation(libs.ktor.client.js) // For Web
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test) // Brings all the platform dependencies automatically

            // MockClient setup
            implementation(libs.ktor.client.mock) // To create a MockEngine
            implementation(libs.kotlinx.coroutines.test) // to have coroutines working for MockEngine

            // Mock classes as you usually would do
            // wanted to have mokkery here. Not yet supported with wasm; as its in alpha
        }

        jvmTest.dependencies {
            // Mock classes as you usually would do
            // wanted to have mokkery here. Not yet supported with wasm; as its in alpha
            implementation(libs.mokkery)
        }

        wasmJsTest.dependencies {

        }
    }
}

