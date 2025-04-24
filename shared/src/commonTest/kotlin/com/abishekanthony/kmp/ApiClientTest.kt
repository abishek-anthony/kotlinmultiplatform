package com.abishekanthony.kmp

import com.abishekanthony.kmp.api.ApiClient
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import kotlin.coroutines.coroutineContext
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ApiClientTest {

    private val client = MockEngine { request ->
        respond(
            content = """
                {
                    "message": "Hello!"
                }
            """.trimIndent(),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    private lateinit var apiClient: ApiClient;

    @BeforeTest
    fun setup() {
        apiClient = ApiClient(HttpClient(client))
    }

    @Test
    fun fetchHello() = runTest {
        val response = apiClient.fetchHello()

        assertEquals("""
                {
                    "message": "Hello!"
                }
            """.trimIndent(), response)
    }
}