package com.example.k2022_03_08_rv.controller

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log

class MediaController {

    var mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    private fun onPrepared(mediaPlayer: MediaPlayer) {
        Log.i("Choreographer", "Started")
        mediaPlayer.start()
    }

    // android docs say to use this method to as it is multithreaded
    fun setAndPrepareRadioLink(url: String) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(url)
        mediaPlayer.setOnPreparedListener(this::onPrepared)
        mediaPlayer.prepareAsync()

    }

}