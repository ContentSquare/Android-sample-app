package com.example.androidsampleapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidsampleapp.analytics.Analytics
import kotlinx.android.synthetic.main.content_gdpr.*

class GdprActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gdpr)
    }

    fun optIn(view: View) {
        Analytics.optIn(this)
        Toast.makeText(applicationContext, "User has Opted In", Toast.LENGTH_SHORT).show()
    }

    fun optOut(view: View) {
        Analytics.optOut(this)
        Toast.makeText(applicationContext, "User has Opted Out", Toast.LENGTH_SHORT).show()
    }

    fun forgetMe(view: View) {
        Analytics.forgetMe()
        Toast.makeText(applicationContext, "Forget Me chosen", Toast.LENGTH_SHORT).show()
    }

    fun stopTracking(view: View) {
        Analytics.stopTracking()
        Toast.makeText(applicationContext, "CS tracking has been paused.", Toast.LENGTH_SHORT).show()
    }

    fun resumeTracking(view: View) {
        Analytics.resumeTracking()
        Toast.makeText(applicationContext, "CS tracking has been resumed.", Toast
                .LENGTH_SHORT).show()
    }

    fun refreshUserIdString(view: View) {
        userid_text.text = String.format("Your userId is: %s", Analytics.provideUserId())
        Toast.makeText(applicationContext, "UserId refreshed", Toast.LENGTH_SHORT).show()
    }
}
