package com.abishekanthony.kmp.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.abishekanthony.kmp.navigation.Route.*

class NavigationViewModel {
    var currentScreen by mutableStateOf(MAIN)
        private set

    fun navigate(to: Route) {
        currentScreen = to
    }
}