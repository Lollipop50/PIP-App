package com.lollipop50.pipapp

import android.app.PictureInPictureParams
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        setUpButtons()
    }

    override fun onPictureInPictureModeChanged(
            isInPictureInPictureMode: Boolean,
            newConfig: Configuration?
    ) {
        if (isInPictureInPictureMode) {
            supportActionBar?.hide()
            buttonOne.visibility = View.INVISIBLE
            buttonTwo.visibility = View.INVISIBLE
        } else {
            supportActionBar?.show()
            buttonOne.visibility = View.VISIBLE
            buttonTwo.visibility = View.VISIBLE
        }
    }

    override fun onUserLeaveHint() {
        launchInPip()
    }

    private fun launchInPip() {
        val pipParams = PictureInPictureParams.Builder().build()
        enterPictureInPictureMode(pipParams)
    }

    private fun setUpButtons() {
        buttonOne.setOnClickListener { Log.e(BUTTONS_TAG, "Button One is clicked") }
        buttonTwo.setOnClickListener { Log.e(BUTTONS_TAG, "Button Two is clicked") }
    }

    companion object {

        private const val BUTTONS_TAG = "BUTTONS"
    }
}