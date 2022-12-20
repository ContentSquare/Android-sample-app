package com.example.androidsampleapp.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.http.HttpHost
import org.apache.http.impl.client.AutoRetryHttpClient
import org.apache.http.message.BasicHttpRequest
import org.apache.http.protocol.BasicHttpContext
import java.net.URI


class HttpClientIntegration : NetworkIntegration {
    private val client = AutoRetryHttpClient()

    override fun sendRequest(
        clientCallTimeoutMs: Long,
        httpMethod: NetworkAnalysisActivity.HttpMethod,
        responseCode: NetworkAnalysisActivity.ResponseCode,
        delay: NetworkAnalysisActivity.Delay,
        callback: (String) -> Unit
    ) {
        val httpStatusUrl = getUrl(responseCode.toString(), delay.toString())

        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {
                val response = client.execute(
                    httpHost(httpStatusUrl),
                    BasicHttpRequest(httpMethod.toString(), httpStatusUrl),
                    BasicHttpContext()
                )
                callback(response.statusLine.toString())
            }
        }
    }

    private fun httpHost(url: String): HttpHost = with(URI(url)) {
        HttpHost(host, port, scheme)
    }
}