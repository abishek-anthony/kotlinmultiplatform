package com.abishekanthony.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform