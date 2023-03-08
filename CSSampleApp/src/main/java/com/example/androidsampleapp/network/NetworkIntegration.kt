package com.example.androidsampleapp.network

interface NetworkIntegration {
    fun sendRequest(
        url: String,
        clientCallTimeoutMs: Long = 10000,
        httpMethod: NetworkAnalysisActivity.HttpMethod,
        callback: (String) -> Unit
    )
}
