package com.example.androidsampleapp.crash

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.androidsampleapp.utils.PreferenceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CrashHelper {

    private const val PREF_CRASH_ON_NEXT_START = "prefcrashonnextstart"
    private const val PREF_CRASH_ON_FOREGROUND = "prefcrashonforeground"
    private const val PREF_CRASH_DELAY = "prefcrashdelay"

    fun crashInCoroutineIODispatcher(scope: LifecycleCoroutineScope) {
        scope.launch(Dispatchers.IO) {
            throwNullPointerException()
        }
    }

    fun crashInCoroutineMainDispatcher(scope: LifecycleCoroutineScope) {
        scope.launch(Dispatchers.Main) {
            throwNullPointerException()
        }
    }

    fun throwNullPointerException() {
        val nullPointerProperty: String? = null
        nullPointerProperty!!.length
    }

    fun throwUninitializedPropertyAccessException() {
        lateinit var uninitialisedProperty: String
        println(uninitialisedProperty)
    }

    fun throwExceptionWithCauses() {
        throw IllegalStateException("first", RuntimeException("second", Throwable("last")))
    }

    fun throwExceptionWithTooManyCauses() {
        tailrec fun exceptionWithCauses(
            count: Int,
            index: Int = count,
            cause: Throwable? = null
        ): Throwable {
            val ex = Throwable("index: $index", cause)
            return if (index != 0) {
                exceptionWithCauses(count, index - 1, ex)
            } else {
                ex
            }
        }
        throw exceptionWithCauses(16)
    }

    fun throwStackOverflowException() {
        fun unstoppable(index: Long = 0): Long {
            return unstoppable(index + 1)
        }
        unstoppable()
    }

    fun throwOutOfMemoryException() {
        var size = 1024
        while (true) {
            size *= 2
            ByteArray(size)
        }
    }

    fun delayedCrash(handler: Handler, delay: Long) {
        handler.postDelayed({ throwNullPointerException() }, delay)
    }

    fun crashOnNextStart(context: Context) {
        PreferenceHelper(context).putBoolean(PREF_CRASH_ON_NEXT_START, true)
    }

    fun crashOnForeground(context: Context) {
        PreferenceHelper(context).putBoolean(PREF_CRASH_ON_FOREGROUND, true)
    }

    fun crashOnForegroundWithDelay(context: Context) {
        crashOnForeground(context)
        PreferenceHelper(context).putLong(PREF_CRASH_DELAY, 5000)
    }

    fun crashInService(context: Context) {
        context.startService(Intent(context, CrashService::class.java))
    }

    fun init(context: Context) {
        val shouldCrash = PreferenceHelper(context).getBoolean(PREF_CRASH_ON_NEXT_START, false)
        if (shouldCrash) {
            PreferenceHelper(context).putBoolean(PREF_CRASH_ON_NEXT_START, false)
            throwNullPointerException()
        }
    }

    fun onActivityResumed(context: Context) {
        val shouldCrash = PreferenceHelper(context).getBoolean(PREF_CRASH_ON_FOREGROUND, false)
        val delay = PreferenceHelper(context).getLong(PREF_CRASH_DELAY, -1L)
        if (shouldCrash) {
            PreferenceHelper(context).putBoolean(PREF_CRASH_ON_FOREGROUND, false)
            if (delay != -1L) {
                PreferenceHelper(context).putLong(PREF_CRASH_DELAY, -1L)
                delayedCrash(Handler(Looper.getMainLooper()), delay)
            } else {
                throwNullPointerException()
            }
        }
    }
}
