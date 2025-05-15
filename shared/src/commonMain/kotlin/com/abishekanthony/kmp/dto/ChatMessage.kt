package com.abishekanthony.kmp.dto

import kotlinx.serialization.Serializable
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Serializable
data class ChatMessage @OptIn(ExperimentalTime::class) constructor(
    val userId: String,
    val text: String,
    val timestampUTC: Long = Clock.System.now().toEpochMilliseconds(),
    val isUser: Boolean
)
