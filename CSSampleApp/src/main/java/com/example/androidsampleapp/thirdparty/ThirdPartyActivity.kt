package com.example.androidsampleapp.thirdparty

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidsampleapp.databinding.ActivityThirdPartyBinding

class ThirdPartyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdPartyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdPartyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun openLaunchDarkly(view: View) {
        startActivity(Intent(this, LaunchDarklyActivity::class.java))
    }

}
