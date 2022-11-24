package com.example.androidsampleapp.network

import com.example.androidsampleapp.network.NetworkAnalysisActivity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class UrlIntegration : NetworkIntegration {

    override fun sendRequest(
        clientCallTimeoutMs: Long,
        httpMethod: HttpMethod,
        responseCode: ResponseCode,
        delay: Delay
    ) {
        val connection =
            URL(
                getUrl(
                    responseCode.toString(),
                    delay.toString()
                )
            ).openConnection() as HttpURLConnection
        connection.requestMethod = HttpMethod.GET.toString()
        try {
            CoroutineScope(Dispatchers.IO).launch {
                kotlin.runCatching {
                    connection.connect()
                }
            }
        } finally {
            connection.disconnect()
        }
    }
}