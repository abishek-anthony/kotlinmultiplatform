package com.abishekanthony.kmp

import com.abishekanthony.kmp.dto.ChatMessage
import com.abishekanthony.kmp.feature.chatgpt.ChatGptPromptController
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun Application.restControllerRouting() {
    val chatGptPromptController = ChatGptPromptController()

    routing {
        authenticate("auth-basic") {
            val userId = "MyChatGPT"
            get("/") {
                call.respond(
                    ChatMessage(
                        userId = userId,
                        text = "Hi, I am MyChatGPT. your Chat Assistant Bot.",
                        isUser = false
                    )
                )

            }
            post("/prompt") {
                call.respond(chatGptPromptController.handlePrompt(call.receive<List<ChatMessage>>()))
            }
        }
    }
}