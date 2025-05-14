package com.abishekanthony.kmp.model

import com.abishekanthony.kmp.data.ChatMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Clock.System
import kotlin.time.ExperimentalTime

class ChatViewModel {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Default)

    @OptIn(ExperimentalTime::class)
    fun sendMessage(content: String, sender: String = "You") {
        if (content.isBlank()) return

        val message = ChatMessage(
            id = (_messages.value.size + 1).toString(),
            sender = sender,
            content = content,
            timestamp = System.now().epochSeconds
        )

        scope.launch {
            _messages.value = _messages.value + message
        }
    }
}