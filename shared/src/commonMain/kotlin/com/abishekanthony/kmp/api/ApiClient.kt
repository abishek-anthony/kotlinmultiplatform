package com.abishekanthony.kmp.api

import com.abishekanthony.kmp.KtorKermitLogger
import com.abishekanthony.kmp.api.Api.Companion.defaultClient
import com.abishekanthony.kmp.config.SERVER_PORT
import com.abishekanthony.kmp.dto.ChatMessage
import io.ktor.client.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.ContentType.*

class ApiClient(
    private val client: HttpClient = defaultClient().config {
        install(Logging) {
            logger = KtorKermitLogger
            level = LogLevel.ALL
            sanitizeHeader { header ->
                header == HttpHeaders.Authorization
            }
        }
    }
) : Api {

    private val logger = KtorKermitLogger

    suspend fun fetch(): ChatMessage {
        logger.log("Fetching data from server...")
        val response = client.request("http://0.0.0.0:${SERVER_PORT}/") {
            basicAuth("jetbrains", "foobar")
            contentType(Application.Json)
            method = HttpMethod.Get
        }

        return response.getBody()
    }

    suspend fun prompt(prompt: List<ChatMessage>): ChatMessage {
        logger.log("Sending prompt to server...")
        val response = client.request("http://0.0.0.0:${SERVER_PORT}/prompt") {
            method = HttpMethod.Post
            basicAuth("jetbrains", "foobar")
            contentType(Application.Json)
            setBody(prompt.toJson())
        }

        return response.getBody()
    }
}

