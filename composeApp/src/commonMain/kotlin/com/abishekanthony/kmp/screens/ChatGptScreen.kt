package com.abishekanthony.kmp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abishekanthony.kmp.components.MyHeaderBar

data class ChatMessage(val text: String, val isUser: Boolean)

@Composable
fun ChatGptScreen(onBack: () -> Unit) {
    var message by remember { mutableStateOf("") }
    var messages by remember { mutableStateOf(listOf<ChatMessage>()) }

    Column(modifier = Modifier.fillMaxSize()) {
        MyHeaderBar(
            title = "ChatGPT",
            onBack = onBack,
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            messages.forEach { msg ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (msg.isUser) Arrangement.End else Arrangement.Start
                ) {
                    Card(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(12.dp),
                        backgroundColor = if (msg.isUser) Color(0xFFE3F2FD) else Color.White,
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 16.dp)
                            .border(
                                width = 1.dp,
                                color = if (msg.isUser) Color(0xFF90CAF9) else Color(0xFFB0BEC5),
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Text(
                            text = msg.text,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            TextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message...") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (message.isNotBlank()) {
                        messages = messages + ChatMessage(message, true) +
                                ChatMessage("Response to: $message", false)
                        message = ""
                    }
                },
                enabled = message.isNotBlank()
            ) {
                Text("Ask")
            }
        }
    }
}
