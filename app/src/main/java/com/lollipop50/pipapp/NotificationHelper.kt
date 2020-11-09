package com.lollipop50.pipapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class NotificationHelper(
    private val context: Context
) {

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun setUpNotificationChannel() {
        if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID,
                    context.getString(R.string.channel_name),
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    description = context.getString(R.string.channel_description)
                }
            )
        }
    }

    companion object {

        const val CHANNEL_ID = "CHANNEL_ID"
        const val NOTIFICATION_ID = 1
    }
}