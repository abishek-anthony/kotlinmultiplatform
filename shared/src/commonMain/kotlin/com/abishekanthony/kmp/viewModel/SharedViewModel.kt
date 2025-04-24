package com.abishekanthony.kmp.viewModel

import com.abishekanthony.kmp.api.Api
import com.abishekanthony.kmp.api.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SharedViewModel(
    private val apiClient: Api = ApiClient()
){
    fun onButtonClick(onResult: (String) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            val result = apiClient.fetchHello()
            withContext(Dispatchers.Main) {
                onResult(result)
            }
        }
    }
}