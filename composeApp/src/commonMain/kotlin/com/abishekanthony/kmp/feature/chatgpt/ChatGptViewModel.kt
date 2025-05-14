package com.abishekanthony.kmp.feature.chatgpt

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.abishekanthony.kmp.dto.ChatMessage
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class ChatGptViewModel() {
    var currentEditFieldMessageByUser by mutableStateOf("")
        private set
    var chatHistory by mutableStateOf(listOf<ChatMessage>())
        private set

    fun onEditFieldMessageChange(newValue: String) {
        currentEditFieldMessageByUser = newValue
    }

    @OptIn(ExperimentalTime::class)
    fun onAskQuestion() {
        chatHistory = chatHistory.plus(
            ChatMessage(
                userId = "1",
                text = currentEditFieldMessageByUser,
                isUser = true,
                timestampUTC = Clock.System.now(),
            )
        )

        chatHistory = chatHistory.plus(
            ChatMessage(
                userId = "GPT",
                text = currentEditFieldMessageByUser,
                isUser = false,
                timestampUTC = Clock.System.now(),
            )
        )
    }

}