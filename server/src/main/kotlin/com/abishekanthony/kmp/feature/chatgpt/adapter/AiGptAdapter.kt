package com.abishekanthony.kmp.feature.chatgpt.adapter

import com.abishekanthony.kmp.api.Api
import com.abishekanthony.kmp.api.getBody
import com.abishekanthony.kmp.api.toJson
import com.abishekanthony.kmp.dto.ChatMessage
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class AiGptAdapter(
    private val client: HttpClient = Api.Companion.defaultClient(),
    private val apiKey: String = "CHANGE-ME",
    private val aiModel: String = "gpt-4o-mini",
) : Api {

    suspend fun executePrompt(prompt: List<OpenAiMessage>): ChatMessage {
        val response: HttpResponse = client.request("https://api.openai.com/v1/chat/completions") {
            method = HttpMethod.Post
            headers {
                append(HttpHeaders.Authorization, "Bearer $apiKey")
                append(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            setBody(
                ChatCompletionRequest(
                    model = aiModel,
                    messages = prompt
                ).toJson()
            )
        }

        val completionResponse = response.getBody<ChatCompletionResponse>()
        val reply = completionResponse.choices.firstOrNull()?.message?.content ?: "No response"

        return ChatMessage(
            userId = aiModel,
            text = reply,
            isUser = false
        )
    }
}