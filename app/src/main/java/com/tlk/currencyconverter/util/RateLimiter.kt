package com.tlk.currencyconverter.util

import android.os.SystemClock
import java.util.concurrent.TimeUnit

class RateLimiter(timeout: Int, timeUnit: TimeUnit) {
    private var lastFetched: Long? = null
    private val timeout = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(): Boolean {
        val now = now()
        if (lastFetched == null) {
            lastFetched = now
            return true
        }
        if (now - lastFetched!! > timeout) {
            lastFetched = now
            return true
        }
        return false
    }

    private fun now() = SystemClock.uptimeMillis()
}