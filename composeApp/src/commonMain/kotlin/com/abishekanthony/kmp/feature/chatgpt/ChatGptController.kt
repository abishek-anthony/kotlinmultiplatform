package com.abishekanthony.kmp.feature.chatgpt

import com.abishekanthony.kmp.api.ApiClient
import com.abishekanthony.kmp.dto.ChatMessage
import io.ktor.client.HttpClient
import io.ktor.http.ContentType.Application.Json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ChatGptController(
    private val client: ApiClient = ApiClient(
        HttpClient {
        }
    ),
) {

    fun start(onResponse: (ChatMessage) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val response = client.fetch()
                println("Response: $response")
                onResponse(response)
            } catch (e: Exception) {
                // Optionally log the error
                println("Error: ${e.message}")
            }
        }
    }
    fun executePrompt(prompt: ChatMessage, onResponse: (ChatMessage) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val response = client.prompt(prompt)
                onResponse(response)
            } catch (e: Exception) {
                // Optionally log the error
                println("Error: ${e.message}")
            }
        }
    }
}