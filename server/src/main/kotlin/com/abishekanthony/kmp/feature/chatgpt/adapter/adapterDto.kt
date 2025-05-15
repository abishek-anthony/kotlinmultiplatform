package com.abishekanthony.kmp.feature.chatgpt.adapter

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class OpenAiMessage(
    val role: String,
    val content: String
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class ChatCompletionRequest(
    val model: String,
    val messages: List<OpenAiMessage>
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class ChatCompletionResponse(
    val choices: List<Choice>
) {
    @Serializable
    @JsonIgnoreUnknownKeys
    data class Choice(
        val message: OpenAiMessage
    )
}