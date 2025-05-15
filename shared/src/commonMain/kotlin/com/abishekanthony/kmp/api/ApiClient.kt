package com.abishekanthony.kmp.api

import com.abishekanthony.kmp.api.Api.Companion.LOCAL_SERVER
import com.abishekanthony.kmp.api.Api.Companion.defaultClient
import com.abishekanthony.kmp.dto.ChatMessage
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.ContentType.*

class ApiClient(
    private val client: HttpClient = defaultClient(),
) : Api {

    suspend fun fetch(): ChatMessage {
        println("Fetching data from server...")
        val response = client.request("$LOCAL_SERVER/") {
            basicAuth("jetbrains", "foobar")
            contentType(Application.Json)
            method = HttpMethod.Get
        }

        return response.getBody()
    }

    suspend fun prompt(prompt: List<ChatMessage>): ChatMessage {
        println("Sending prompt to server...")
        val response = client.request("$LOCAL_SERVER/prompt") {
            method = HttpMethod.Post
            basicAuth("jetbrains", "foobar")
            contentType(Application.Json)
            setBody(prompt.toJson())
        }

        return response.getBody()
    }
}

