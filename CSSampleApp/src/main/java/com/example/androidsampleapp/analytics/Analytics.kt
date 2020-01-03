package com.example.androidsampleapp.analytics

import android.content.Context
import android.util.Log
import com.contentsquare.android.ContentSquare
import com.contentsquare.android.api.model.Transaction

object Analytics {

    private val TAG = Analytics::class.java.simpleName

    fun tagScreen(screenName: String) {
        Log.i(TAG, "Screen: $screenName")
        ContentSquare.send(screenName)
    }

    fun pushTransaction(amount: Float, currency: Int, id: String) {
        ContentSquare.send(Transaction.builder(amount, currency).id(id).build())
        Log.i(TAG, "Transaction: $amount - ID: $id")
    }

    fun stopTracking() {
        ContentSquare.stopTracking()
    }

    fun resumeTracking() {
        ContentSquare.resumeTracking()
    }

    fun forgetMe() {
        ContentSquare.forgetMe()
    }

    fun optIn(context: Context) {
        ContentSquare.optIn(context)
    }

    fun optOut(context: Context) {
        ContentSquare.optOut(context)
    }

    fun provideUserId(): String {
        return ContentSquare.getUserId()
    }
}