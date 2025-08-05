package com.abishekanthony.kmp.feature.chatgpt

import androidx.compose.runtime.RecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.abishekanthony.kmp.KtorKermitLogger
import com.abishekanthony.kmp.dto.ChatMessage
import com.abishekanthony.kmp.exception.ExceptionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.time.ExperimentalTime

class ChatGptViewModel(
    private val controller: ChatGptController = ChatGptController(),
): ViewModel() {
    private val logger = KtorKermitLogger

    var isError: Boolean by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set


    var currentEditFieldMessageByUser by mutableStateOf("")
        private set
    var chatHistory by mutableStateOf(listOf<ChatMessage>())
        private set

    fun getWelcomeMessage(onResponse: (ChatMessage) -> Unit) {
        controller.start(
            onSuccess = {
                onResponse(it)
            },
            onError = {
                showError(it)
            }
        )
    }

    fun load() {
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
            prompt = chatHistory,
            onSuccess = { response ->
                chatHistory = chatHistory.plus(
                    response
                )
            },
            onError = { showError(it) }
        )
    }

    fun showError(exception: ExceptionType) {
        logger.log("Exception type: $exception")
        logger.log("isError=$isError")
        logger.log("isErrorMessage=$isError")
        isError = true
        errorMessage = exception.message.ifBlank {
            "Unknown error"
        }
        logger.log("isError=$isError")
        logger.log("isErrorMessage=$isError")
    }

    fun dismissError() {
        logger.log("dismissError()")
        isError = false
        errorMessage = ""
    }
}