package com.abishekanthony.kmp.api

import com.abishekanthony.kmp.config.SERVER_PORT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiClient(
    private val client: HttpClient = HttpClient()
) : Api {

    override suspend fun fetchHello(): String {
        return client.get("http://0.0.0.0:${SERVER_PORT}/hello").body()
    }
}