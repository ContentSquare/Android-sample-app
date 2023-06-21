package com.example.androidsampleapp.useridentifier

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.contentsquare.android.Contentsquare
import com.example.androidsampleapp.databinding.ActivityUserIdentifierBinding

class UserIdentifierActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserIdentifierBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserIdentifierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendUserIdentifier.setOnClickListener {
            Contentsquare.sendUserIdentifier(binding.userIdentifierTextView.text.toString())
        }
    }
}
