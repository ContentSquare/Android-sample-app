package com.example.androidsampleapp.crash

import android.content.Context
import android.os.Handler
import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.androidsampleapp.R

sealed class CrashType(@StringRes val nameRes: Int, val performCrash: () -> Unit) {

    object CrashNullPointerException : CrashType(
        R.string.activity_crash_null_pointer_exception,
        { CrashHelper.throwNullPointerException() }
    )

    object CrashUninitializedException : CrashType(
        R.string.activity_crash_uninitialised,
        { CrashHelper.throwUninitializedPropertyAccessException() }
    )

    object CrashMultipleCausesException : CrashType(
        R.string.activity_crash_causes,
        { CrashHelper.throwExceptionWithCauses() }
    )

    object CrashTooManyCausesException : CrashType(
        R.string.activity_crash_too_many_causes,
        { CrashHelper.throwExceptionWithTooManyCauses() }
    )

    object CrashStackOverflowException : CrashType(
        R.string.activity_crash_stack_overflow,
        { CrashHelper.throwStackOverflowException() }
    )

    object CrashOutOfMemoryException : CrashType(
        R.string.activity_crash_out_of_memory,
        { CrashHelper.throwOutOfMemoryException() }
    )

    class CrashInCoroutineMainDispatcher(scope: LifecycleCoroutineScope) : CrashType(
        R.string.activity_crash_coroutines_main,
        { CrashHelper.crashInCoroutineMainDispatcher(scope) }
    )

    class CrashInCoroutineIODispatcher(scope: LifecycleCoroutineScope) : CrashType(
        R.string.activity_crash_coroutines_io,
        { CrashHelper.crashInCoroutineIODispatcher(scope) }
    )

    class CrashInService(context: Context) : CrashType(
        R.string.activity_crash_service,
        { CrashHelper.crashInService(context) }
    )

    class CrashInFiveSeconds(handler: Handler) : CrashType(
        R.string.activity_crash_delayed,
        { CrashHelper.delayedCrash(handler, 5000) }
    )

    class CrashOnNextStart(context: Context) : CrashType(
        R.string.activity_crash_next_start,
        { CrashHelper.crashOnNextStart(context) }
    )

    class CrashOnForeground(context: Context) : CrashType(
        R.string.activity_crash_foreground,
        { CrashHelper.crashOnForeground(context) }
    )

    class CrashOnForegroundWithDelay(context: Context) : CrashType(
        R.string.activity_crash_foreground_with_delay,
        { CrashHelper.crashOnForegroundWithDelay(context) }
    )
}
