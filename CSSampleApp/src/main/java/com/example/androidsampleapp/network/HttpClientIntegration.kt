package com.example.androidsampleapp.network

import android.util.Log
import org.apache.http.HttpResponse
import org.apache.http.NameValuePair
import org.apache.http.StatusLine
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.HttpClient
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.message.BasicNameValuePair
import java.io.IOException
import java.io.UnsupportedEncodingException


class HttpClientIntegration : NetworkIntegration {

    override fun sendRequest(
        url: String,
        clientCallTimeoutMs: Long,
        httpMethod: NetworkAnalysisActivity.HttpMethod,
        responseCode: NetworkAnalysisActivity.ResponseCode,
        delay: NetworkAnalysisActivity.Delay
    ) {
        val client: HttpClient = DefaultHttpClient()
        val post = HttpPost(
            getUrl(
                responseCode.toString(),
                delay.toString()
            )
        )
        val nameValuePair: MutableList<NameValuePair> = ArrayList(2)
        nameValuePair.add(BasicNameValuePair("name", "username"))
        nameValuePair.add(BasicNameValuePair("word", "8090509239"))
        try {
            post.entity = UrlEncodedFormEntity(nameValuePair)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        try {
            val response: HttpResponse = client.execute(post)
            val statusLine: StatusLine = response.statusLine
            Log.d("Http Response:", response.toString())
            Log.d("statusline:", "" + statusLine)
        } catch (e: ClientProtocolException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}