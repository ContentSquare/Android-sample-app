package com.example.androidsampleapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidsampleapp.analytics.Analytics
import com.example.androidsampleapp.databinding.ActivityDynamicVariablesBinding


class DynamicVariablesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDynamicVariablesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDynamicVariablesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onSendText(view: View) {
        Analytics.send(binding.textKey.text.toString(), binding.textValue.text.toString())
    }

    fun onSendNumber(view: View) {
        Analytics.send(
            binding.numericKey.text.toString(),
            binding.numericValue.text.toString().toLong()
        )
    }
}
