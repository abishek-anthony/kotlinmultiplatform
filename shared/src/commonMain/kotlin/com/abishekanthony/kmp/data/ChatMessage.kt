package com.abishekanthony.kmp.data

data class ChatMessage(
    val id: String,
    val sender: String,
    val content: String,
    val timestamp: Long
)