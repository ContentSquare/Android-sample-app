package com.example.androidsampleapp.network

import com.example.androidsampleapp.network.NetworkAnalysisActivity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

class OkHttpIntegration : NetworkIntegration {

    private lateinit var client: OkHttpClient

    override fun sendRequest(
        clientCallTimeoutMs: Long,
        httpMethod: HttpMethod,
        responseCode: ResponseCode,
        delay: Delay
    ) {
        client = OkHttpClient().newBuilder()
            .callTimeout(clientCallTimeoutMs, TimeUnit.MILLISECONDS)
            .build()

        val request: Request = when (httpMethod) {
            HttpMethod.GET -> {
                Request.Builder()
                    .url(getUrl(responseCode.toString(), delay.toString()))
                    .build()
            }
            HttpMethod.POST -> {
                Request.Builder()
                    .url(getUrl(responseCode.toString(), delay.toString()))
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "text/plain")
                    .post("{key1:\"value1\"}".toRequestBody("text/x-markdown; charset=utf-8".toMediaType()))
                    .build()
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {
                client.newCall(request).execute()
            }
        }
    }
}