package com.abishekanthony.kmp.config

interface Platform {
    val name: String
}

// Note: This means that we will have platform-specific implementation of getPlatform
// see actual fun getPlatform(): Platform
// important it needs to be in same package!
expect fun getPlatform(): Platform