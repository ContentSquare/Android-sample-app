package com.example.androidsampleapp


import android.util.Log
import com.contentsquare.android.core.features.http.HttpConnection.HttpResponse
import com.contentsquare.android.core.features.http.HttpRequestHandler
import com.contentsquare.android.core.features.http.RequestOptions
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit

class HttpRequestHandlerImplCustomer: HttpRequestHandler {

    private val client = OkHttpClient()

    override fun handleGet(options: RequestOptions): HttpResponse {
        Log.d("****", "${Thread.currentThread().name}: Starting GET with 'OkHttp' customer side implementation ")
        val csResponse = HttpResponse()
        try {
            val newClient = client.newBuilder()
                .connectTimeout(options.timeoutConnect.toLong(), TimeUnit.MILLISECONDS)
                .readTimeout(options.timeoutRead.toLong(), TimeUnit.MILLISECONDS)
                .build()
            val request = Request.Builder().url(options.endpoint)
            for (header in options.headers) {
                request.addHeader(header.key, header.value)
            }

            newClient.newCall(request.build()).execute().use { response ->
                if (!response.isSuccessful) {
                    csResponse.exception = IOException("Unexpected code $response")
                } else {
                    // Get Response
                    csResponse.status = response.code
                    csResponse.endpoint = response.request.url.toString()
                    csResponse.headers = response.headers.toMultimap()
                    csResponse.stringResponse = response.body!!.string()
                }
            }
        } catch (e: Exception) {
            csResponse.exception = e
        }
        return csResponse
    }

    override fun handlePost(options: RequestOptions, data: ByteArray?): HttpResponse {
        Log.d("****", "${Thread.currentThread().name}: Starting POST with 'OkHttp' customer side implementation ")
        val csResponse = HttpResponse()

        try {
            val newClient = client.newBuilder()
                .connectTimeout(options.timeoutConnect.toLong(), TimeUnit.MILLISECONDS)
                .readTimeout(options.timeoutRead.toLong(), TimeUnit.MILLISECONDS)
                .build()
            val request = Request.Builder().url(options.endpoint)
                .post(data!!.toRequestBody("application/json; charset=${Charsets.UTF_8.name()}".toMediaType()))
            for (header in options.headers) {
                request.addHeader(header.key, header.value)
            }

            newClient.newCall(request.build()).execute().use { response ->
                if (!response.isSuccessful) {
                    csResponse.exception = IOException("Unexpected code $response")
                } else {
                    // Get Response
                    csResponse.status = response.code
                    csResponse.endpoint = response.request.url.toString()
                    csResponse.dataSentBytes = data.size.toLong()
                    csResponse.timeSpentMsec = response.receivedResponseAtMillis - response.sentRequestAtMillis
                    csResponse.headers = response.headers.toMultimap()
                    csResponse.stringResponse = response.body!!.string()
                }
            }
        } catch (e: Exception) {
            csResponse.exception = e
        }

        return csResponse
    }
}