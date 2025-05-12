plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "com.abishekanthony.kmp"
version = "1.0.0"
application {
    mainClass.set("com.abishekanthony.kmp.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.api.cors)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}