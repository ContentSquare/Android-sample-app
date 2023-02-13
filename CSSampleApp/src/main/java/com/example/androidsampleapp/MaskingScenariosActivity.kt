package com.example.androidsampleapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.contentsquare.android.Contentsquare
import com.example.androidsampleapp.analytics.Analytics
import com.example.androidsampleapp.databinding.ActivityMaskingScenariosBinding

class MaskingScenariosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMaskingScenariosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaskingScenariosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        Analytics.tagScreen("Masking-Scenarios-Activity")
    }

    fun onSetDefaultMaskingOn(view: View) {
        Contentsquare.setDefaultMasking(true)
    }

    fun onSetDefaultMaskingOff(view: View) {
        Contentsquare.setDefaultMasking(false)
    }

    fun onMaskAllTextView(view: View) {
        Contentsquare.mask(TextView::class.java)
    }

    fun onUnmaskAllTextView(view: View) {
        Contentsquare.unMask(TextView::class.java)
    }

    fun onMaskAllImageView(view: View) {
        Contentsquare.mask(ImageView::class.java)
    }

    fun onUnmaskAllImageView(view: View) {
        Contentsquare.unMask(ImageView::class.java)
    }

    fun onUnmaskRedImageView(view: View) {
        Contentsquare.mask(binding.redImageView)
    }

    fun onMaskRedImageView(view: View) {
        Contentsquare.mask(binding.redImageView)
    }
}