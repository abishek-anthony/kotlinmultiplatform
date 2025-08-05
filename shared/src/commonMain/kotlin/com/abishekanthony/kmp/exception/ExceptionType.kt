package com.abishekanthony.kmp.exception

import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest

open class ExceptionType(open val code: HttpStatusCode, override val message: String) :
    Exception("HttpStatusCode {$code}: $message}")

abstract class ServerFaultException(override val code: HttpStatusCode, override val message: String) :
    ExceptionType(code, message = "ServerFaultException: $message")

abstract class UsersFaultException(override val code: HttpStatusCode, override val message: String) :
    ExceptionType(code, message = "UsersFaultException: $message")

// This are specialized exceptions and variants of the above
class NetworkException(status: HttpStatusCode, override val message: String) :
    ServerFaultException(status, message)

class BadRequest(message1: HttpStatusCode, override val message: String) : UsersFaultException(BadRequest, message)