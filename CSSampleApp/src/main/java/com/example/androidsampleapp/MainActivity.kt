package com.example.androidsampleapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.contentsquare.android.api.Currencies
import com.example.androidsampleapp.analytics.Analytics
import com.example.androidsampleapp.databinding.ActivityMainBinding
import com.example.androidsampleapp.fragment.MainFragmentActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onResume() {
        super.onResume()
        Analytics.tagScreen("Main-Activity")
    }

    fun openActivity(view: View) {
        startActivity(Intent(this, SimpleActivity::class.java))
    }

    fun openFragment(view: View) {
        startActivity(Intent(this, MainFragmentActivity::class.java))
    }

    fun openPrivacyOptions(view: View) {
        startActivity(Intent(this, GdprActivity::class.java))
    }

    fun openDynamicVariables(view: View) {
        startActivity(Intent(this, DynamicVariablesActivity::class.java))
    }

    fun openPopUp(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("App Bar color")
        builder.setMessage("Set the App bar color to RED?")

        builder.setPositiveButton("YES") { _, _ ->
            Toast.makeText(
                applicationContext, "Ok, we changed the App bar color.", Toast
                    .LENGTH_SHORT
            ).show()
            binding.toolbar.setBackgroundColor(Color.RED)
        }

        builder.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                applicationContext, "Ok, back to its original color.", Toast
                    .LENGTH_SHORT
            )
                .show()
            binding.toolbar.setBackgroundColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.colorPrimary
                )
            )
        }

        builder.setNeutralButton("Cancel") { _, _ ->
            Toast.makeText(applicationContext, "You cancelled the dialog.", Toast.LENGTH_SHORT)
                .show()
        }

        builder.setOnDismissListener {
            //After the Popup is dismissed. The base activity lifecycle remains the same, Therefore
            // we need to manually generate an activity ScreenView for the base Activity.
            Analytics.tagScreen("Main-Activity")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
        Analytics.tagScreen("ActivityDialog_DialogShow")
    }

    fun pushTransaction(view: View) {
        val amount = 244.33f
        val currency = Currencies.EUR
        val id = "1233455"
        Analytics.pushTransaction(amount, currency, id)
        Toast.makeText(
            applicationContext, "Transaction id $id is being tracked", Toast
                .LENGTH_SHORT
        ).show()
    }

    fun pushTransactionString(view: View) {
        val amount = 234.33f
        val currencyString = "USD"
        val transactionId = "1111"
        Analytics.pushTransaction(amount, currencyString, transactionId)
        Toast.makeText(
            applicationContext, "Transaction id $transactionId is being tracked", Toast
                .LENGTH_SHORT
        ).show()
    }
}