package com.oysm.securepass

import android.app.Application
import android.util.Log

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("App", "Application Started - Secure Password Manager")
    }
}