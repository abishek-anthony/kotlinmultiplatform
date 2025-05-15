package com.abishekanthony.kmp.feature.chatgpt

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.abishekanthony.kmp.dto.ChatMessage
import kotlin.time.ExperimentalTime

class ChatGptViewModel(
    private val controller: ChatGptController = ChatGptController(),
) {
    var currentEditFieldMessageByUser by mutableStateOf("")
        private set
    var chatHistory by mutableStateOf(listOf<ChatMessage>())
        private set

    fun getWelcomeMessage(onResponse: (ChatMessage) -> Unit) {
        controller.start {
            onResponse(it)
        }
    }

    init {
        getWelcomeMessage { chatHistory = chatHistory.plus(it) }
    }

    fun onEditFieldMessageChange(newValue: String) {
        currentEditFieldMessageByUser = newValue
    }

    @OptIn(ExperimentalTime::class)
    fun onAskQuestion() {
        val usersPrompt = ChatMessage(
            userId = "1",
            text = currentEditFieldMessageByUser,
            isUser = true
        )
        chatHistory = chatHistory.plus(
            usersPrompt
        )

        controller.executePrompt(
            prompt = usersPrompt,
            onResponse = { response ->
                chatHistory = chatHistory.plus(
                    response
                )
            }
        )
    }

}