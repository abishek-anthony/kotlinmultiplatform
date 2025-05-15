package com.abishekanthony.kmp.feature.chatgpt

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abishekanthony.kmp.component.ChatBubble
import com.abishekanthony.kmp.component.EditFieldWithApplyButton
import com.abishekanthony.kmp.component.MyHeaderBar
import com.abishekanthony.kmp.component.screen.RowsScreenWithHeaderBar

@Composable
fun ChatGptScreen(
    viewModel: ChatGptViewModel = ChatGptViewModel(),
    onBack: () -> Unit
) {
    RowsScreenWithHeaderBar(
        title = "ChatGpt",
        onBack = onBack
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
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
