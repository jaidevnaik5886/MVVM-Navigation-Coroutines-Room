package com.example.tentwentymovies

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.example.tentwentymovies.common.BaseActivity
import com.example.tentwentymovies.utils.AppConstants
import com.example.tentwentymovies.utils.HttpConstants
import kotlinx.android.synthetic.main.activity_main.*


class VideoActivty : BaseActivity() {

    lateinit var videoKey : String

    private var mCurrentPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       videoKey =  intent.getStringExtra(AppConstants.MOVIE_PLAYBACKID)

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME)
        }

        val controller = MediaController(this)
        controller.setMediaPlayer(videoview)
        videoview.setMediaController(controller)
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            videoview!!.pause()
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(PLAYBACK_TIME, videoview!!.currentPosition)
    }

    private fun initializePlayer() {
        buffering_textview!!.visibility = VideoView.VISIBLE

        val videoUri = getMedia(HttpConstants.BASE_VIDEO_URL + videoKey)
        videoview!!.setVideoURI(videoUri)

        videoview!!.setOnPreparedListener {
            buffering_textview!!.visibility = VideoView.INVISIBLE

            if (mCurrentPosition > 0) {
                videoview!!.seekTo(mCurrentPosition)
            } else {
                videoview!!.seekTo(1)
            }

            videoview!!.start()
        }

        videoview!!.setOnCompletionListener {
            Toast.makeText(
                this@VideoActivty,
                "complete",
                Toast.LENGTH_SHORT
            ).show()

            videoview!!.seekTo(0)
        }
    }

    private fun releasePlayer() {
        videoview!!.stopPlayback()
    }

    private fun getMedia(mediaName: String): Uri {
        return if (URLUtil.isValidUrl(mediaName)) {
            Uri.parse(mediaName)
        } else {
            Uri.parse(
                "android.resource://" + packageName +
                        "/raw/" + mediaName
            )
        }
    }

    companion object {
        private const val PLAYBACK_TIME = "play_time"
    }
}