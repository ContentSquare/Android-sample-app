package com.example.androidsampleapp.crash

import android.app.Service
import android.content.Intent
import android.os.IBinder

class CrashService : Service() {

    override fun onCreate() {
        CrashHelper.throwNullPointerException()
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
