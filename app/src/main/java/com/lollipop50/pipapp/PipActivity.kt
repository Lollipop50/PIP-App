package com.lollipop50.pipapp

import android.app.PictureInPictureParams
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pip.*

class PipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pip)

        launchInPipButton.setOnClickListener { launchInPip() }
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        if (isInPictureInPictureMode) {
            supportActionBar?.hide()
            launchInPipButton.visibility = View.INVISIBLE
        } else {
            supportActionBar?.show()
            launchInPipButton.visibility = View.VISIBLE
        }
    }

    override fun onUserLeaveHint() {
        launchInPip()
    }

    private fun launchInPip() {
        val pipParams = PictureInPictureParams.Builder().build()
        enterPictureInPictureMode(pipParams)
    }
}