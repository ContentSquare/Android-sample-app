package com.example.androidsampleapp.thirdparty

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.androidsampleapp.R
import com.example.androidsampleapp.databinding.ActivityLaunchDarklyBinding
import com.launchdarkly.sdk.ContextKind
import com.launchdarkly.sdk.LDContext
import com.launchdarkly.sdk.android.ConnectionInformation
import com.launchdarkly.sdk.android.LDClient
import com.launchdarkly.sdk.android.LDConfig

class LaunchDarklyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLaunchDarklyBinding
    private var ldClient: LDClient? = null

    companion object {
        const val LAUNCH_DARKLY_MOBILE_KEY = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaunchDarklyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.initialiseClient.visibility = conditionalVisibility(ldClient == null)
        binding.labelSetupLaunchDarkly.visibility = conditionalVisibility(
            LAUNCH_DARKLY_MOBILE_KEY.isEmpty()
        )
        binding.featureFlagName.addTextChangedListener {
            binding.retrieveAndListen.isEnabled = it?.isEmpty() == false
        }
    }

    fun onRetrieveAndListen(view: View) {
        val flagName = binding.featureFlagName.text.toString()
        val flagValue = ldClient?.boolVariation(flagName, false)

        binding.featureFlagState.text = "Initial state: $flagValue"
        ldClient?.registerFeatureFlagListener(flagName) {
            Log.e("STEO", "OnChange for " +flagName)
            val changedFlagValue = ldClient?.boolVariation(flagName, false)
            binding.featureFlagState.append("\nFlag changed to: $changedFlagValue")
        }
    }

    fun onStartLaunchDarklyClient(view: View) {
        val ldConfig = LDConfig.Builder(LDConfig.Builder.AutoEnvAttributes.Enabled)
            .mobileKey(LAUNCH_DARKLY_MOBILE_KEY)
            .build()

        val ctx = LDContext.builder(ContextKind.DEFAULT, "Sample Key")
            .name("Sample Name")
            .build()

//        ldClient = LDClient.init(this.application, ldConfig, ctx, 0)
        LDClient.init(this.application, ldConfig, ctx)
        ldClient = LDClient.get()
        if (ldClient?.connectionInformation?.connectionMode != ConnectionInformation.ConnectionMode.STREAMING) {
            binding.initialiseClient.setText(R.string.label_launch_darkly_init_client_failed)
        } else {
            binding.initialiseClient.visibility = View.GONE
            binding.featureFlagName.visibility = View.VISIBLE
            binding.retrieveAndListen.visibility = View.VISIBLE
        }
    }

    private fun conditionalVisibility(condition: Boolean): Int {
        return if (condition) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
