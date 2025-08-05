package com.abishekanthony.kmp

import io.ktor.client.plugins.logging.*
import co.touchlab.kermit.Logger as KermitLogger

object KtorKermitLogger : Logger {
    private val kermit = KermitLogger
        .withTag("KtorHttp")

    override fun log(message: String) {
        kermit.i { message }
    }
}