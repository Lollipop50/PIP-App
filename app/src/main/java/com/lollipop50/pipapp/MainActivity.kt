package com.lollipop50.pipapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openPipActivityButton.setOnClickListener { openPipActivity() }
        openVideoActivityButton.setOnClickListener { openVideoActivity() }
    }

    private fun openPipActivity() {
        startActivity(Intent(this, PipActivity::class.java))
    }

    private fun openVideoActivity() {
        startActivity(Intent(this, VideoActivity::class.java))
    }
}