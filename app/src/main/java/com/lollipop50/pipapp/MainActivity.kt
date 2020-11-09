package com.lollipop50.pipapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpButtons()
    }

    private fun setUpButtons() {
        runServiceButton.setOnClickListener { runService() }
        stopServiceButton.setOnClickListener { stopService() }

        runForegroundServiceButton.setOnClickListener { runForegroundService() }
        stopForegroundServiceButton.setOnClickListener { stopForegroundService() }
    }

    private fun runService() {
        startService(Intent(this, TestService::class.java))
    }

    private fun stopService() {
        stopService(Intent(this, TestService::class.java))
    }

    private fun runForegroundService() {
        startService(Intent(this, TestForegroundService::class.java))
    }

    private fun stopForegroundService() {
        stopService(Intent(this, TestForegroundService::class.java))
    }
}