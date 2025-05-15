package com.abishekanthony.kmp

import androidx.compose.runtime.Composable
import com.abishekanthony.kmp.navigation.NavigationController
import com.abishekanthony.kmp.navigation.NavigationViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    navigationViewModel: NavigationViewModel = NavigationViewModel(),
) {
    NavigationController(navigationViewModel)
}