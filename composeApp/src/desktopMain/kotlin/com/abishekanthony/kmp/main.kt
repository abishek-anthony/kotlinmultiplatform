package com.abishekanthony.kmp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.abishekanthony.kmp.model.ChatViewModel

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Desktop Chat") {
        val viewModel = remember { ChatViewModel() }
        ChatScreen(viewModel)
    }
}