package com.abishekanthony.kmp

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abishekanthony.kmp.data.ChatMessage
import com.abishekanthony.kmp.model.ChatViewModel

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    val messages by viewModel.messages.collectAsState()
    var inputText by remember { mutableStateOf("") }

    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            // Messages list
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth().padding(bottom = 8.dp)
            ) {
                items(messages) { msg ->
                    MessageBubble(msg)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            // Input + send
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type your message...") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        viewModel.sendMessage(inputText)
                        inputText = ""
                    },
                    enabled = inputText.isNotBlank()
                ) {
                    Text("Send")
                }
            }
        }
    }
}

@Composable
fun MessageBubble(message: ChatMessage) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "${message.sender}:",
            style = MaterialTheme.typography.subtitle2
        )
        Text(
            text = message.content,
            style = MaterialTheme.typography.body1
        )
    }
}
