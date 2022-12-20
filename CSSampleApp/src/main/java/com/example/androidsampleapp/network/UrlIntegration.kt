package com.example.androidsampleapp.network

import com.example.androidsampleapp.network.NetworkAnalysisActivity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class UrlIntegration : NetworkIntegration {

    override fun sendRequest(
        clientCallTimeoutMs: Long,
        httpMethod: HttpMethod,
        responseCode: ResponseCode,
        delay: Delay,
        callback: (String) -> Unit
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
                    val response: String = if (connection.responseCode in 100..399) {
                        connection.inputStream
                    } else {
                        connection.errorStream
                    }.bufferedReader().use(BufferedReader::readText)
                    callback(response)
                }
            }
        } finally {
            connection.disconnect()
        }
    }
}