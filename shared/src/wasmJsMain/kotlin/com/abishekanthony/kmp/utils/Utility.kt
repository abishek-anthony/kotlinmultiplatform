package com.abishekanthony.kmp.utils

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.websocket.WebSockets
import kotlin.js.*
import org.w3c.notifications.Notification
import org.w3c.notifications.NotificationOptions
actual fun createHttpClient(): HttpClient {
    return HttpClient(Js) {
        install(WebSockets)
    }
}

// Top-level function to show a notification
/*@OptIn(ExperimentalJsExport::class)
actual fun showNotification(title: String, message: String) {
    if (js("typeof Notification !== 'undefined'")) {
        val permission = Notification.permission

        if (permission == "granted") {
            Notification(title, NotificationOptions(body = message))
        } else if (permission != "denied") {
            Notification.requestPermission { result ->
                if (result == "granted") {
                    Notification(title, NotificationOptions(body = message))
                } else {
                    console.log("Notification permission not granted")
                }
            }
        } else {
            console.log("Notification permission denied")
        }
    } else {
        console.log("Notification API is not supported in this browser")
    }
}*/

actual fun showNotification(title: String, message: String) {
    if (isNotificationPermissionGranted()) {
        Notification(title, NotificationOptions(body = message))
    } else {
        js("console.log('Notification permission not granted')")
    }
}

// Top-level function to request notification permission
actual fun requestNotificationPermission() {
    js("Notification.requestPermission()")
}

// Helper function to check if notification permission is granted
fun isNotificationPermissionGranted(): Boolean {
    return js("Notification.permission === 'granted'") as Boolean
}
