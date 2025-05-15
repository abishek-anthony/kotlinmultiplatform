package com.abishekanthony.kmp.feature.chatgpt.adapter

import kotlinx.serialization.Serializable

@Serializable
data class OpenAiMessage(
    val role: String,
    val content: String
)

@Serializable
data class ChatCompletionRequest(
    val model: String,
    val messages: List<OpenAiMessage>
)

@Serializable
data class ChatCompletionResponse(
    val choices: List<Choice>
) {
    @Serializable
    data class Choice(
        val message: OpenAiMessage
    )
}