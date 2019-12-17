package com.example.androidsampleapp.analytics

import android.util.Log
import com.contentsquare.android.ContentSquare

object Analytics {

    private val TAG = Analytics::class.java.simpleName

    fun tagScreen(screenName: String) {
        Log.i(TAG, "Screen: $screenName")
        ContentSquare.send(screenName)
    }

}