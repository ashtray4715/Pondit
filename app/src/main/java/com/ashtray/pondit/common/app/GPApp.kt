package com.ashtray.pondit.common.app

import android.app.Application

class GPApp: Application() {

    companion object {
        private lateinit var factory: GPFactory

        fun getFactory(): GPFactory = factory
    }

    override fun onCreate() {
        super.onCreate()
        factory = GPFactory()
    }
}