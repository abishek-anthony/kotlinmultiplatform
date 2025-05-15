package com.abishekanthony.kmp.api

import com.abishekanthony.kmp.config.SERVER_PORT
import com.abishekanthony.kmp.dto.ChatMessage
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ApiClient(
    private val client: HttpClient = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    },
) : Api {
    private val SERVER = "http://0.0.0.0:${SERVER_PORT}"

    suspend fun fetch(): ChatMessage {
        println("Fetching data from server...")
        val response = client.request("$SERVER/") {
            contentType(Application.Json)
            method = HttpMethod.Get
        }

        return response.getBody()
    }

    suspend fun prompt(prompt: ChatMessage): ChatMessage {
        println("Sending prompt to server...")
        val response = client.request("$SERVER/prompt") {
            method = HttpMethod.Post
            contentType(Application.Json)
            setBody(prompt.toJson())
        }

        return response.getBody()
    }
}