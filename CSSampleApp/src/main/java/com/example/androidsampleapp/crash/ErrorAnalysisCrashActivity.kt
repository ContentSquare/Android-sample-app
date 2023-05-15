package com.example.androidsampleapp.crash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.example.androidsampleapp.R
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

    companion object {
        private const val MAPPING_ID_RESOURCE_IDENTIFIER = "contentsquare_mapping_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityErrorCrashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val crashTypeAdapter = CrashTypeAdapter(this@ErrorAnalysisCrashActivity, crashTypes)
            crashType.adapter = crashTypeAdapter

            performCrash.setOnClickListener { (crashType.selectedItem as CrashType).performCrash() }

            val mappingIdRes = resources.getIdentifier(
                MAPPING_ID_RESOURCE_IDENTIFIER,
                "string",
                packageName
            )

            val res = if (mappingIdRes == ResourcesCompat.ID_NULL) {
                getString(R.string.activity_crash_mapping_id_not_available)
            } else {
                getString(mappingIdRes)
            }

            mappingFileId.text = resources.getString(R.string.activity_crash_mapping_id, res)
        }
    }
}
