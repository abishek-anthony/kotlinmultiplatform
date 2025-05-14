package com.abishekanthony.kmp.dto

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class ChatMessage @OptIn(ExperimentalTime::class) constructor(
    val userId: String,
    val text: String,
    val timestampUTC: Instant,
    val isUser: Boolean
)
