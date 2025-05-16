package com.abishekanthony.kmp.utils

import io.ktor.client.HttpClient

expect fun createHttpClient(): HttpClient
expect fun showNotification(title: String, message: String)
expect fun requestNotificationPermission()