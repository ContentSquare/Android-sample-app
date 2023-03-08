package com.example.androidsampleapp.network

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.contentsquare.android.error.analysis.ErrorAnalysis
import com.example.androidsampleapp.databinding.ActivityNetworkAnalysisBinding

class NetworkAnalysisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNetworkAnalysisBinding
    private lateinit var libraryAdapter: ArrayAdapter<Library>
    private lateinit var httpAdapter: ArrayAdapter<HttpMethod>
    private lateinit var responseCodeAdapter: ArrayAdapter<ResponseCode>
    private lateinit var delayAdapter: ArrayAdapter<Delay>

    companion object {
        const val URL = "https://httpstat.us"
        const val EXTRA_PATH = "person/123/store/456"
        const val EXTRA_EMAIL = "test@cs.com"
        const val URL_MASK = "$URL/:status_code/person/:person_id/store/:store_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkAnalysisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ErrorAnalysis.setUrlMaskingPatterns(listOf(URL_MASK))
        binding.extraPath.text = EXTRA_PATH

        libraryAdapter = ArrayAdapter<Library>(
            this,
            android.R.layout.simple_spinner_item,
            Library.values()
        )
        libraryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.librarySpinner.apply {
            adapter = libraryAdapter
            setSelection(0)
        }

        httpAdapter = ArrayAdapter<HttpMethod>(
            this,
            android.R.layout.simple_spinner_item,
            HttpMethod.values()
        )
        httpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.httpSpinner.apply {
            adapter = httpAdapter
            setSelection(0)
        }

        responseCodeAdapter = ArrayAdapter<ResponseCode>(
            this,
            android.R.layout.simple_spinner_item,
            ResponseCode.values()
        )
        responseCodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.responseCodeSpinner.apply {
            adapter = responseCodeAdapter
            setSelection(0)
        }

        delayAdapter = ArrayAdapter<Delay>(
            this,
            android.R.layout.simple_spinner_item,
            Delay.values()
        )
        delayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.delaySpinner.apply {
            selectedItem
            adapter = delayAdapter
            setSelection(0)
        }
        binding.sendButton.setOnClickListener {
            getLibraryRequestBuilder().sendRequest(
                url = getUrl(),
                httpMethod = binding.httpSpinner.selectedItem as HttpMethod,
                callback = this::displayToast
            )
        }
    }

    private fun displayToast(result: String) {
        runOnUiThread {
            Toast.makeText(this, "Request result : $result", Toast.LENGTH_LONG).show()
        }
    }

    private fun getLibraryRequestBuilder() = when (binding.librarySpinner.selectedItem) {
        Library.OKHTTP -> OkHttpIntegration()
        else -> UrlIntegration()
    }

    private fun getUrl(): String {
        val delay = (binding.delaySpinner.selectedItem as Delay).toString()
        val email = if (binding.checkboxEmailUrl.isChecked) "/$EXTRA_EMAIL" else ""
        val extraPath = if (binding.checkboxExtraPath.isChecked) "/$EXTRA_PATH" else ""
        val responseCode = (binding.responseCodeSpinner.selectedItem as ResponseCode).toString()

        return "$URL/$responseCode$extraPath$email?sleep=$delay&test=testparam"
    }

    enum class Library(private val libraryName: String) {
        OKHTTP("OkHttp"),
        URL("URL");

        override fun toString(): String {
            return libraryName
        }
    }

    enum class HttpMethod(private val methodName: String) {
        GET("GET"),
        POST("POST");

        override fun toString(): String {
            return methodName
        }
    }

    enum class ResponseCode(private val responseCode: Int) {
        RESPONSE_200(200),
        RESPONSE_201(201),
        RESPONSE_300(300),
        RESPONSE_400(400),
        RESPONSE_401(401),
        RESPONSE_500(500),
        RESPONSE_501(501);

        override fun toString(): String {
            return responseCode.toString()
        }
    }

    enum class Delay(private val delay: Int) {
        DELAY_0(0),
        DELAY_100(100),
        DELAY_300(300),
        DELAY_600(600),
        DELAY_1000(1000),
        DELAY_2000(2000),
        DELAY_4000(4000);

        override fun toString(): String {
            return delay.toString()
        }
    }
}
