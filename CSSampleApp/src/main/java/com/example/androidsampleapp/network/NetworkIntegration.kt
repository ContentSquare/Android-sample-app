package com.example.androidsampleapp.network

interface NetworkIntegration {
    fun sendRequest(
        url: String,
        clientCallTimeoutMs: Long = 10000,
        httpMethod: NetworkAnalysisActivity.HttpMethod,
        responseCode: NetworkAnalysisActivity.ResponseCode,
        delay: NetworkAnalysisActivity.Delay
    )

    fun getUrl(responseCode: String, delay: String) =
        "https://httpstat.us/$responseCode?sleep=$delay"
}