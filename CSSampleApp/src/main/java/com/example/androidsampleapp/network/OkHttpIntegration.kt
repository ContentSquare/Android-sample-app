package com.example.androidsampleapp.network

import android.util.Log
import com.contentsquare.android.error.analysis.network.CsErrorAnalysisInterceptor
import com.example.androidsampleapp.network.NetworkAnalysisActivity.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit

class OkHttpIntegration : NetworkIntegration {

    private lateinit var client: OkHttpClient

    override fun sendRequest(
        url: String,
        clientCallTimeoutMs: Long,
        httpMethod: HttpMethod,
        responseCode: ResponseCode,
        delay: Delay
    ) {
        client = OkHttpClient().newBuilder()
            .addInterceptor(CsErrorAnalysisInterceptor())
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
        Log.d("F&F", "Request : $request")

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("ACU", response.code.toString())
                response.use {
                    if (!response.isSuccessful)
                        Log.d("F&F", "Demo App : Unsuccessful response")
                    else
                        Log.d("F&F", "Demo App : Successful response")
                }
            }
        })
    }
}