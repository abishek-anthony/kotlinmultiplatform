package com.abishekanthony.kmp.feature.chatgpt

import com.abishekanthony.kmp.api.ApiClient
import com.abishekanthony.kmp.dto.ChatMessage
import com.abishekanthony.kmp.exception.ExceptionType
import com.abishekanthony.kmp.exception.handle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatGptController(
    private val client: ApiClient = ApiClient(),
) {

    fun start(onSuccess: (ChatMessage) -> Unit, onError: (ExceptionType) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            handle(
                onSuccess = {
                    withContext(Dispatchers.Main) {
                        onSuccess(client.fetch())
                    }
                },
                onException = {
                    withContext(Dispatchers.Main) {
                        onError(it)
                    }
                }
            )
        }
    }

    fun executePrompt(prompt: List<ChatMessage>, onSuccess: (ChatMessage) -> Unit, onError: (ExceptionType) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            handle(
                onSuccess = {
                    withContext(Dispatchers.Main) {
                        onSuccess(client.prompt(prompt))
                    }
                },
                onException = {
                    withContext(Dispatchers.Main) {
                        onError(it)
                    }
                }
            )
        }
    }
}