package com.abishekanthony.kmp

import com.abishekanthony.kmp.dto.ChatMessage
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun Application.restControllerRouting() {
    routing {
        val userId = "J.A.B.I"
        get("/") {
            call.respond(
                ChatMessage(
                    userId = userId,
                    text = "Hi, I am J.A.B.I. your Chat Assistant Bot.",
                    isUser = false
                )
            )

        }
        post("/prompt") {
            val chatMessage = call.receive<ChatMessage>()
            // Here you can process the chat message and generate a response
            // For now, we will just echo the message back with a timestamp and isUser flag = false
            call.respond(chatMessage.copy(userId = userId, isUser = false))
        }
    }
}