package com.abishekanthony.kmp.feature.chatgpt

import com.abishekanthony.kmp.dto.ChatMessage
import com.abishekanthony.kmp.feature.chatgpt.adapter.AiGptAdapter
import com.abishekanthony.kmp.feature.chatgpt.adapter.OpenAiMessage

class ChatGptPromptController(
    private val aiGptAdapter: AiGptAdapter = AiGptAdapter()
) {
    suspend fun handlePrompt(prompt: List<ChatMessage>): ChatMessage = aiGptAdapter.executePrompt(
        prompt.map {
            OpenAiMessage(
                role = if (it.isUser) "user" else "assistant",
                content = it.text
            )
        }
    )

}