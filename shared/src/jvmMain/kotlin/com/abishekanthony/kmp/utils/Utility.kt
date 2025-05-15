package com.abishekanthony.kmp.utils

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*

actual fun createHttpClient(): HttpClient {
    return HttpClient(CIO) {
        install(WebSockets)
    }
}