package com.lollipop50.pipapp

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager

class TestForegroundService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var layoutInflater: LayoutInflater

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(SERVICES_TAG, "FS: onStartCommand()")
        showNotification()
        showWindows()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        Log.e(SERVICES_TAG, "FS: onBind()")
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(SERVICES_TAG, "FS: onCreate()")

        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onDestroy() {
        stopForeground(true)
        Log.e(SERVICES_TAG, "FS: stopForeground()")

        Log.e(SERVICES_TAG, "FS: onDestroy()")
        super.onDestroy()
    }

    private fun showNotification() {
        val notificationHelper = NotificationHelper(this)
        notificationHelper.setUpNotificationChannel()

        val contentIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, ForegroundServiceActivity::class.java),
            0
        )

        val notification: Notification = Notification.Builder(this, NotificationHelper.CHANNEL_ID)
            .setContentTitle(getText(R.string.notification_title))
            .setContentText(getText(R.string.notification_message))
            .setSmallIcon(R.drawable.ic_android)
            .setContentIntent(contentIntent)
            .setTicker(getText(R.string.ticker_text))
            .build()

        startForeground(NotificationHelper.NOTIFICATION_ID, notification)
        Log.e(SERVICES_TAG, "FS: startForeground()")
    }

    private fun showWindows() {
        val viewsList = listOf(
            layoutInflater.inflate(R.layout.overlay_window_layout, null),
            layoutInflater.inflate(R.layout.overlay_window_layout, null),
            layoutInflater.inflate(R.layout.overlay_window_layout, null),
            layoutInflater.inflate(R.layout.overlay_window_layout, null)
        )

        val customParamsList = listOf(
            getDefaultLayoutParams().apply { gravity = Gravity.TOP or Gravity.START },
            getDefaultLayoutParams().apply { gravity = Gravity.TOP or Gravity.END },
            getDefaultLayoutParams().apply { gravity = Gravity.BOTTOM or Gravity.START },
            getDefaultLayoutParams().apply { gravity = Gravity.BOTTOM or Gravity.END }
        )

        viewsList.forEachIndexed { index, view ->
            windowManager.addView(view, customParamsList[index])
            view.setOnClickListener { windowManager.removeView(view) }
        }
    }

    private fun getDefaultLayoutParams() = WindowManager.LayoutParams(
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSLUCENT
    )

    companion object {

        private const val SERVICES_TAG = "SERVICES"
    }
}