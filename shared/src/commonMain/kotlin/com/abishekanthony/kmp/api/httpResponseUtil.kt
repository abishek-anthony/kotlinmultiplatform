package com.abishekanthony.kmp.api

import com.abishekanthony.kmp.exception.BadRequest
import com.abishekanthony.kmp.exception.NetworkException
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

suspend inline fun <reified T> HttpResponse.getBody(): T {
    when (this.status.value) {
        in 200..299 -> {
            return this.bodyAsText().toDto()
        }
        in 400..499 -> {
            throw BadRequest(this.status, this.bodyAsText())
        }
        else -> {
            throw NetworkException(this.status, this.bodyAsText())
        }
    }
}

val lenientJson = Json {
    ignoreUnknownKeys = true
}

inline fun <reified T> T.toJson(): String {
    return lenientJson.encodeToString<T>(this)
}

inline fun <reified T> String.toDto(): T {
    return lenientJson.decodeFromString(this)
}