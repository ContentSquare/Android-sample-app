package com.example.androidsampleapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androidsampleapp.analytics.Analytics
import com.example.androidsampleapp.fragment.MainFragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        Analytics.tagScreen("Main-Activity")
    }

    fun openActivity(view: View) {
        val intent = Intent(this, SimpleActivity::class.java)
        startActivity(intent)
    }

    fun openFragment(view: View) {
        val intent = Intent(this, MainFragmentActivity::class.java)
        startActivity(intent)
    }

    fun openPopUp(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("App Bar color")
        builder.setMessage("Set the App bar color to RED?")

        // Display Positive button
        builder.setPositiveButton("YES") { dialog, which ->
            Toast.makeText(applicationContext, "Ok, we changed the App bar color.", Toast
                    .LENGTH_SHORT).show()
            toolbar.setBackgroundColor(Color.RED)
            Analytics.tagScreen("ActivityDialog_DialogYES")
        }

        // Display Negative button
        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(applicationContext, "Ok, back to its original color.", Toast
                    .LENGTH_SHORT)
                    .show()
            toolbar.setBackgroundColor(ContextCompat.getColor(applicationContext,
                    R.color.colorPrimary))
            Analytics.tagScreen("ActivityDialog_DialogNO")
        }

        // Display Neutral button
        builder.setNeutralButton("Cancel") { _, _ ->
            Toast.makeText(applicationContext, "You cancelled the dialog.", Toast.LENGTH_SHORT).show()
            Analytics.tagScreen("ActivityDialog_DialogCancel")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
        Analytics.tagScreen("ActivityDialog_DialogShow")
    }

}
