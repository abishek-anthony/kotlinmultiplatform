package com.abishekanthony.kmp.service

import com.abishekanthony.kmp.data.ChatMessage
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ChatClient(private val client: HttpClient) {
    private val _messages = MutableSharedFlow<String>()
    val messages: SharedFlow<String> = _messages.asSharedFlow()

    private var session: WebSocketSession? = null
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun connect(host: String, port: Int, path: String) {
        scope.launch {
            try {
                client.webSocket(
                    method = HttpMethod.Get,
                    host = host,
                    port = port,
                    path = path
                ) {
                    println("Connected to WebSocket server")
                    session = this

                    for (frame in incoming) {
                        if (frame is Frame.Text) {
                            receiveMessage(frame.readText())
                        }
                    }
                }
            } catch (e: Exception) {
                println("WebSocket error: ${e.message}")
            }
        }
    }

    fun send(message: String) {
        scope.launch {
            session?.send(Frame.Text(message))
        }
    }

    private suspend fun receiveMessage(rawMessage: String) {
        val message = kotlinx.serialization.json.Json.decodeFromString(ChatMessage.serializer(), rawMessage)
        println("Received from server: Sender=${message.sender}, Content=${message.content}")
        _messages.emit(rawMessage)
    }
}