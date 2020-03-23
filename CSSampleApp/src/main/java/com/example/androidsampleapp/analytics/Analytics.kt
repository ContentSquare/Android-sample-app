package com.example.androidsampleapp.analytics

import android.content.Context
import android.util.Log
import com.contentsquare.android.Contentsquare
import com.contentsquare.android.api.model.Transaction

object Analytics {

    private val TAG = Analytics::class.java.simpleName

    fun tagScreen(screenName: String) {
        Log.i(TAG, "Screen: $screenName")
        Contentsquare.send(screenName)
    }

    fun pushTransaction(amount: Float, currency: Int, id: String) {
        Contentsquare.send(Transaction.builder(amount, currency).id(id).build())
        Log.i(TAG, "Transaction: $amount - ID: $id")
    }

    fun pushTransaction(amount: Float, currency: String, id: String){
        Contentsquare.send(Transaction.builder(amount, currency).id(id).build())
        Log.i(TAG, "Transaction: $amount $currency - ID: $id")
    }

    fun stopTracking() {
        Contentsquare.stopTracking()
    }

    fun resumeTracking() {
        Contentsquare.resumeTracking()
    }

    fun forgetMe() {
        Contentsquare.forgetMe()
    }

    fun optIn(context: Context) {
        Contentsquare.optIn(context)
    }

    fun optOut(context: Context) {
        Contentsquare.optOut(context)
    }

    fun provideUserId(): String {
        return Contentsquare.getUserId()
    }

    fun send(key: String, value: String) {
        Contentsquare.send(key, value)
    }

    fun send(key: String, value: Long) {
        Contentsquare.send(key, value)
    }
}