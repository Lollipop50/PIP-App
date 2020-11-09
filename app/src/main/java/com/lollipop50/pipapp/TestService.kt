package com.lollipop50.pipapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class TestService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(SERVICES_TAG, "S: onStartCommand()")

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        Log.e(SERVICES_TAG, "S: onBind()")
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()

        Log.e(SERVICES_TAG, "S: onCreate()")
    }

    override fun onDestroy() {
        Log.e(SERVICES_TAG, "S: onDestroy()")

        super.onDestroy()
    }

    companion object {

        private const val SERVICES_TAG = "SERVICES"
    }
}