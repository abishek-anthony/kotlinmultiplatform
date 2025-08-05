package com.abishekanthony.kmp.exception

import com.abishekanthony.kmp.KtorKermitLogger
import io.ktor.http.*


suspend fun handle(
    onSuccess: suspend () -> Unit,
    onException: suspend (ExceptionType) -> Unit
) {

    val logger = KtorKermitLogger

    try {
        onSuccess()
    } catch (e: ExceptionType) {
        logger.log("Exception type: $e")
        onException(e)
    } catch (e: Exception) {
        logger.log("Exception type: $e")
        onException(ExceptionType(code = HttpStatusCode.InternalServerError, message = e.message ?: "Unknown error"))
    }
}