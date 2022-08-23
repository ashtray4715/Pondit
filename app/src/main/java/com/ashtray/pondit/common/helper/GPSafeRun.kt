package com.ashtray.pondit.common.helper

class GPSafeRun(runnable: Runnable) {

    private val mTag = "GPSafeRun"

    init {
        try {
            runnable.run()
        } catch (e: Exception) {
            GPLog.e(mTag, "safe run catches an error")
            e.printStackTrace()
        }
    }
}