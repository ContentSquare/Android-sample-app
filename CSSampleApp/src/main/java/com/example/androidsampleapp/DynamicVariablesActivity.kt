package com.example.androidsampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidsampleapp.analytics.Analytics
import com.example.androidsampleapp.databinding.ActivityDynamicVariablesBinding


class DynamicVariablesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDynamicVariablesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dynamic_variables)
        binding.activity = this
    }

    fun onSendText() {
        Analytics.send(binding.textKey.text.toString(), binding.textValue.text.toString())
    }

    fun onSendNumber() {
        Analytics.send(
            binding.numericKey.text.toString(),
            binding.numericValue.text.toString().toLong()
        )
    }
}
