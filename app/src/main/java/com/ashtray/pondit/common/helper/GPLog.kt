package com.ashtray.pondit.common.helper

import android.util.Log
import com.ashtray.pondit.BuildConfig

object GPLog {
    private const val TAG = "[mg]"

    fun d(tag: String, message: String?) {
        if (BuildConfig.ENABLE_LOG_PRINTING) {
            Log.d("$TAG[$tag]", message ?: "")
        }
    }

    fun e(tag: String, message: String?) {
        if (BuildConfig.ENABLE_LOG_PRINTING) {
            Log.e("$TAG[$tag]", message ?: "")
        }
    }
}