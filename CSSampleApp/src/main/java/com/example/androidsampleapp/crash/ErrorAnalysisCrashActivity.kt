package com.example.androidsampleapp.crash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidsampleapp.databinding.ActivityErrorCrashBinding

class ErrorAnalysisCrashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityErrorCrashBinding
    private val crashTypes = listOf(
        CrashType.CrashNullPointerException,
        CrashType.CrashUninitializedException,
        CrashType.CrashMultipleCausesException,
        CrashType.CrashTooManyCausesException,
        CrashType.CrashStackOverflowException,
        CrashType.CrashOutOfMemoryException,
        CrashType.CrashInCoroutineMainDispatcher(lifecycleScope),
        CrashType.CrashInCoroutineIODispatcher(lifecycleScope),
        CrashType.CrashInService(this),
        CrashType.CrashInFiveSeconds(Handler(Looper.getMainLooper())),
        CrashType.CrashOnNextStart(this),
        CrashType.CrashOnForeground(this),
        CrashType.CrashOnForegroundWithDelay(this)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityErrorCrashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val crashTypeAdapter = CrashTypeAdapter(this@ErrorAnalysisCrashActivity, crashTypes)
            crashType.adapter = crashTypeAdapter

            performCrash.setOnClickListener { (crashType.selectedItem as CrashType).performCrash() }
        }
    }
}
