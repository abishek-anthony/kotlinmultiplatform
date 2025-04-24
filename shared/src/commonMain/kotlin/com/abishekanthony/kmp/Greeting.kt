package com.abishekanthony.kmp

import com.abishekanthony.kmp.config.Platform
import com.abishekanthony.kmp.config.getPlatform

class Greeting(
    private val platform: Platform = getPlatform()
) {

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}