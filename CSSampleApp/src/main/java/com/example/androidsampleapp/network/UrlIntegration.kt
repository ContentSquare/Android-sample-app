package com.example.androidsampleapp.network

import com.example.androidsampleapp.network.NetworkAnalysisActivity.HttpMethod
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class UrlIntegration : NetworkIntegration {

    override fun sendRequest(
        url: String,
        clientCallTimeoutMs: Long,
        httpMethod: HttpMethod,
        callback: (String) -> Unit
    ) {
        val connection = URL(url).openConnection() as HttpURLConnection
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
