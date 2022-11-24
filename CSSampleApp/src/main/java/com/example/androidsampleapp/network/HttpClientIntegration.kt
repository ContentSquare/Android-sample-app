package com.example.androidsampleapp.network

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
        delay: NetworkAnalysisActivity.Delay
    ) {
        val httpStatusUrl = getUrl(responseCode.toString(), delay.toString())

        client.execute(
            httpHost(httpStatusUrl),
            BasicHttpRequest(httpMethod.toString(), httpStatusUrl),
            BasicHttpContext()
        )
    }

    private fun httpHost(url: String): HttpHost = with(URI(url)) {
        HttpHost(host, port, scheme)
    }
}