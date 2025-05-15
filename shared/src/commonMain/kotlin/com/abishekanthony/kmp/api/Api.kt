package com.abishekanthony.kmp.api

import com.abishekanthony.kmp.config.SERVER_PORT
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

interface Api {
    companion object{
        val LOCAL_SERVER = "http://0.0.0.0:${SERVER_PORT}"

        fun defaultClient(more: () -> Unit = {}): HttpClient = HttpClient() {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            more()
        }
    }
}