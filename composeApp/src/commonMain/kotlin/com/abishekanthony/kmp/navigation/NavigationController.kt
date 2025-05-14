package com.abishekanthony.kmp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.abishekanthony.kmp.screens.ChatGptScreen

@Composable
fun NavigationController(
    viewModel: NavigationViewModel,
) {
    when (viewModel.currentScreen) {
        Route.MAIN -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { viewModel.navigate(Route.CHATGPT) }) {
                Text("Click Me")
            }
        }

        Route.CHATGPT -> ChatGptScreen(onBack = { viewModel.navigate(Route.MAIN) })
    }
}