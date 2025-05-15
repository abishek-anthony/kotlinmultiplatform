package com.abishekanthony.kmp.api

import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

suspend inline fun <reified T> HttpResponse.getBody(): T {
    if (this.status == HttpStatusCode.OK) {
        println(this.bodyAsText())
        return this.bodyAsText().toDto()
    } else {
        throw Exception(this.status.description)
    }
}

val lenientJson = Json {
    ignoreUnknownKeys = true
}

inline fun <reified T> T.toJson(): String {
    return lenientJson.encodeToString(this)
}

inline fun <reified T> String.toDto(): T {
    return lenientJson.decodeFromString(this)
}