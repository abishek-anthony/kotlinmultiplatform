package com.abishekanthony.kmp.feature.chatgpt

import com.abishekanthony.kmp.api.ApiClient
import com.abishekanthony.kmp.dto.ChatMessage
import io.ktor.client.*
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
            onResponse(client.fetch())
        }
    }

    fun executePrompt(prompt: List<ChatMessage>, onResponse: (ChatMessage) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            onResponse(client.prompt(prompt))
        }
    }
}