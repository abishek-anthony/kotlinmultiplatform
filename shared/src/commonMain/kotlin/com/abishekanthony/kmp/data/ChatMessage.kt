package com.abishekanthony.kmp.data

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val id: String,
    val sender: String,
    val content: String,
    val timestamp: Long
)