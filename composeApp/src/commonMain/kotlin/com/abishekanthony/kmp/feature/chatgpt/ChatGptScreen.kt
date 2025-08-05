package com.abishekanthony.kmp.feature.chatgpt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abishekanthony.kmp.component.ChatBubble
import com.abishekanthony.kmp.component.EditFieldWithApplyButton
import com.abishekanthony.kmp.component.screen.RowsScreenWithHeaderBar

@Composable
fun ChatGptScreen(
    viewModel: ChatGptViewModel = ChatGptViewModel(),
    onBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.load()
    }
    if (viewModel.isError) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissError() },
            title = { Text("Error") },
            text = { Text(viewModel.errorMessage) },
            confirmButton = {
                Button(onClick = { viewModel.dismissError() }) {
                    Text("OK")
                }
            })
    }
    RowsScreenWithHeaderBar(
        title = "ChatGpt", onBack = onBack
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.weight(1f).padding(8.dp).verticalScroll(rememberScrollState())
            ) {
                viewModel.chatHistory.onEach {
                    ChatBubble(
                        chatMessage = it,
                        arrangeHorizontalLeft = !it.isUser,
                        leftArrangementColor = Color.White,
                        rightArrangementColor = Color(0xFFE3F2FD),
                    )
                }
            }
            EditFieldWithApplyButton(
                message = viewModel.currentEditFieldMessageByUser,
                onMessageChange = { viewModel.onEditFieldMessageChange(it) },
                onApplyClick = { viewModel.onAskQuestion() },
                placeHolder = "Type your message",
                buttonText = "Send"
            )
        }
    }

}
