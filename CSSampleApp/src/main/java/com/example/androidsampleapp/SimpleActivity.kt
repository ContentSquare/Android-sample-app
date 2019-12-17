package com.example.androidsampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidsampleapp.analytics.Analytics

class SimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
    }

    override fun onResume() {
        super.onResume()
        Analytics.tagScreen("Simple-Activity")
    }
}
