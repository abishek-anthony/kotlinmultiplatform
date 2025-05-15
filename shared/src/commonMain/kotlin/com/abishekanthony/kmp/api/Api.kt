package com.abishekanthony.kmp.api

import com.abishekanthony.kmp.config.SERVER_PORT
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface Api {
    companion object{
        val LOCAL_SERVER = "http://0.0.0.0:${SERVER_PORT}"

        fun defaultClient(): HttpClient = HttpClient() {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}