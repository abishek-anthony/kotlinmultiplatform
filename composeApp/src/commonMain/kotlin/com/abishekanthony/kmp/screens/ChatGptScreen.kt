package com.abishekanthony.kmp.screens

import androidx.compose.runtime.Composable
import com.abishekanthony.kmp.components.MyHeaderBar

@Composable
fun ChatGptScreen(onBack: () -> Unit) {
    MyHeaderBar(
        title = "ChatGPT",
        onBack = onBack,
    )
}