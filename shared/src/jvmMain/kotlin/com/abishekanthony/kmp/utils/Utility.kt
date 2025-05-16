package com.abishekanthony.kmp.utils

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import java.awt.*
import java.awt.TrayIcon.MessageType

actual fun createHttpClient(): HttpClient {
    return HttpClient(CIO) {
        install(WebSockets)
    }
}

actual fun showNotification(title: String, message: String) {
    if (!SystemTray.isSupported()) return

    val tray = SystemTray.getSystemTray()
    val image = Toolkit.getDefaultToolkit().createImage("")
    val trayIcon = TrayIcon(image, "Chat App")
    trayIcon.isImageAutoSize = true
    trayIcon.toolTip = "Chat App"

    try {
        tray.add(trayIcon)
        trayIcon.displayMessage(title, message, MessageType.INFO)
        tray.remove(trayIcon)
    } catch (e: Exception) {
        println("Notification error: ${e.message}")
    }
}

actual fun requestNotificationPermission() {
    // No-op for JVM
}