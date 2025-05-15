package com.abishekanthony.kmp.model

import com.abishekanthony.kmp.data.ChatMessage
import com.abishekanthony.kmp.service.ChatClient
import com.abishekanthony.kmp.utils.createHttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.time.Clock.System
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
class ChatViewModel {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val client = ChatClient(createHttpClient())
    private var myId: String = ""

    fun setUserName(name: String) {
        myId = name
    }

    init {
        client.connect("localhost", 8080, "/chat")

        CoroutineScope(Dispatchers.Default).launch {
            client.messages.collect { raw ->
                println("Received from server: $raw") // Debug log
                val message = kotlinx.serialization.json.Json.decodeFromString(ChatMessage.serializer(), raw)
                val sender = if (message.sender == myId) "You" else message.sender
                val msg = ChatMessage(
                    id = (_messages.value.size + 1).toString(),
                    sender = sender,
                    content = message.content,
                    timestamp = message.timestamp
                )
                _messages.value = _messages.value + msg

            }
        }
    }

    @OptIn(ExperimentalTime::class)
    fun sendMessage(content: String) {
        if (content.isBlank()) return

        val myMsg = ChatMessage(
            id = (_messages.value.size + 1).toString(),
            sender = myId,
            content = content,
            timestamp = System.now().epochSeconds
        )
        val jsonMessage = kotlinx.serialization.json.Json.encodeToString(ChatMessage.serializer(), myMsg)
        client.send(jsonMessage)
    }
}