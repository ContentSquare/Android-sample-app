package com.example.androidsampleapp.network

import com.example.androidsampleapp.network.NetworkAnalysisActivity.*
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
            connection.connect()
            connection.responseCode
        } finally {
            connection.disconnect()
        }
    }
}