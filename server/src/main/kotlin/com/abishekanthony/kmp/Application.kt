package com.abishekanthony.kmp

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.time.Duration.Companion.seconds

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "localhost") {
        install(WebSockets) {
            pingPeriod = 15.seconds
        }
        install(CORS) {
            anyHost() // Allow all hosts
            allowMethod(HttpMethod.Get)
            allowMethod(HttpMethod.Post)
            allowHeader(HttpHeaders.ContentType)
            allowHeader(HttpHeaders.Accept)
        }

        routing {
            val connections = ConcurrentHashMap.newKeySet<DefaultWebSocketServerSession>()

            webSocket("/chat") {
                connections += this
                println("New client connected")

                for (frame in incoming) {
                    if (frame is Frame.Text) {
                        val message = frame.readText()
                        println("Broadcasting message: $message to ${connections.size} clients") // Debug log

                        // Broadcast to all connections
                        for (conn in connections) {
                            conn.send(message)
                        }
                    }
                }

                // Clean up on disconnect
                connections -= this
                println("Client disconnected")
            }
        }
    }.start(wait = true)
}
