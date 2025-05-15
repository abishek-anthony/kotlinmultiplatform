package com.abishekanthony.kmp.api

import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

suspend inline fun <reified T> HttpResponse.getBody(): T {
    if (this.status == HttpStatusCode.OK) {
        return this.bodyAsText().toDto()
    } else {
        println("Error: ${this.status}")
        throw Exception("An error occurred: ${this.status}")
    }
}

inline fun <reified T> T.toJson(): String {
    return Json.encodeToString(this)
}

inline fun <reified T> String.toDto(): T {
    return Json.decodeFromString(this)
}