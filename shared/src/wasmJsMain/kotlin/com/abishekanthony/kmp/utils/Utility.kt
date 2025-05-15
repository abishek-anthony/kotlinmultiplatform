package com.abishekanthony.kmp.utils

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.websocket.WebSockets

actual fun createHttpClient(): HttpClient {
    return HttpClient(Js) {
        install(WebSockets)
    }
}