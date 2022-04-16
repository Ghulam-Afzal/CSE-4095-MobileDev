package com.example.k2022_04_04c

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaDrm
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.view.SurfaceHolder
import android.view.WindowManager
import android.widget.*
import com.example.k2022_04_04c.Services.MediaServices

lateinit var mediaServices: MediaServices
private var bound: Boolean = false
private var message: Message = Message()

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener, MediaPlayer.OnDrmInfoListener,
    SurfaceHolder.Callback, SeekBar.OnSeekBarChangeListener {

    private val videoStr = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
    private lateinit var radioToggle: Button
    private lateinit var videoView: VideoView
    private lateinit var videoToggle: Button
    private lateinit var mediaController: MediaController
    private lateinit var mediaPlayer: MediaPlayer
    private var videoOn: Boolean = false
    private var firstTimeOn: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        radioToggle = findViewById(R.id.radio_toggle_button)
        videoView = findViewById(R.id.videoView)
        videoToggle = findViewById(R.id.video_toggle_button)

        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        videoView.holder.addCallback(this)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnPreparedListener(this)

        radioToggle.setOnClickListener{
            val value = mediaServices.getInt()
            message.what = 1
            mediaServices.radioToggle()
            mediaServices.SelectMedia(1)
            mediaServices.sendMessagge(message,1)
            Toast.makeText(applicationContext,"Hello: $value", Toast.LENGTH_SHORT).show()
        }

        videoToggle.setOnClickListener {
            if (videoOn) {
                mediaPlayer.pause()
                mediaPlayer.stop()
            } else {
                if (firstTimeOn) {
                    mediaPlayer.prepareAsync()
                    firstTimeOn = !firstTimeOn
                } else {
                    mediaPlayer.reset()
                    mediaPlayer.setDataSource(applicationContext, Uri.parse(videoStr))
                    mediaPlayer.prepareAsync()
                }
            }
            videoOn = ! videoOn
        }
    }

    override fun onStart() {
        super.onStart()

        val intent = Intent(this, MediaServices::class.java)
        intent.putExtra("Greeting","Hello world!")
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()

        unbindService(connection)
        bound = false
    }

    private val connection: ServiceConnection = object: ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            val binder: MediaServices.LocalBinder = service as MediaServices.LocalBinder
            mediaServices = binder.getInstance()
            bound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
           bound = false
        }

    }

    // functions
    override fun onPrepared(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
    }

    override fun onDrmInfo(mediaPlayer: MediaPlayer, drmInfo: MediaPlayer.DrmInfo?) {
        mediaPlayer.apply {
            val key = drmInfo?.supportedSchemes?.get(0)
            key?.let {
                prepareDrm(key)
                val keyRequest = getKeyRequest(
                    null, null, null,
                    MediaDrm.KEY_TYPE_STREAMING, null
                )
                provideKeyResponse(null, keyRequest.data)
            }
        }
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        mediaPlayer.apply {
            setOnDrmInfoListener(this@MainActivity)
            setDataSource(applicationContext, Uri.parse(videoStr))
            setDisplay(surfaceHolder)
        }
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
         mediaPlayer.apply {
             setDisplay(p0)
         }
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        TODO("Not yet implemented")
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        TODO("Not yet implemented")
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        TODO("Not yet implemented")
    }

}