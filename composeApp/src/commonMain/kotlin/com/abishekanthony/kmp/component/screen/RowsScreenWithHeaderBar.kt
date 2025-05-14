package com.abishekanthony.kmp.component.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abishekanthony.kmp.component.MyHeaderBar

@Composable
fun RowsScreenWithHeaderBar(
    title: String,
    onBack: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        MyHeaderBar(title = title, onBack = onBack)
        content()
    }
}