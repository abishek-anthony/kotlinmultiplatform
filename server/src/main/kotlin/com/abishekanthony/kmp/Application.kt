package com.abishekanthony.kmp

import com.abishekanthony.kmp.config.SERVER_PORT
import com.abishekanthony.kmp.configs.corsConfigs
import com.abishekanthony.kmp.configs.serializationConfigs
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.event.Level
import kotlin.time.ExperimentalTime

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

@OptIn(ExperimentalTime::class)
fun Application.module() {
    corsConfigs()
    serializationConfigs()
    restControllerRouting()
}

