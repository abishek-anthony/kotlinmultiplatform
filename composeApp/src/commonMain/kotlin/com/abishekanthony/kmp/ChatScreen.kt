package com.abishekanthony.kmp

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.abishekanthony.kmp.data.ChatMessage
import com.abishekanthony.kmp.model.ChatViewModel

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    var showNameDialog by remember { mutableStateOf(true) }
    var userName by remember { mutableStateOf("") }
    val messages by viewModel.messages.collectAsState()
    var inputText by remember { mutableStateOf("") }

    if (showNameDialog) {
        Dialog(onDismissRequest = { /* Prevent dismiss */ }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Enter your name", style = MaterialTheme.typography.h6)
                    TextField(
                        value = userName,
                        onValueChange = { userName = it },
                        placeholder = { Text("Your name") }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {
                                if (userName.isNotBlank()) {
                                    viewModel.setUserName(userName)
                                    showNameDialog = false
                                }
                            }
                        ) {
                            Text("OK")
                        }
                    }
                }
            }
        }
    } else {
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
}

@Composable
fun MessageBubble(message: ChatMessage) {
    Column(modifier = Modifier.fillMaxWidth()) {
        println("${message.sender}: ${message.content}")

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
