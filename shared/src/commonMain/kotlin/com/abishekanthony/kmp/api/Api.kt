package com.abishekanthony.kmp.api

interface Api {

    suspend fun fetchHello(): String
}