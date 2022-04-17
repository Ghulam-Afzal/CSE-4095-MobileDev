package com.example.k2022_04_04c

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaDrm
import android.media.MediaPlayer
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.SurfaceHolder
import android.view.WindowManager
import android.widget.*
import com.example.k2022_04_04c.Services.MediaServices
import java.util.Calendar.SECOND

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
    private lateinit var totalTime: TextView
    private lateinit var seekBar: SeekBar
    private lateinit var textProgress: TextView
    private lateinit var runnable: Runnable
    private lateinit var radio2Toggle: Button
    private lateinit var radio3Toggle: Button
    private var handler = Handler(Looper.getMainLooper())
    private var videoOn: Boolean = false
    private var firstTimeOn: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        radioToggle = findViewById(R.id.radio_toggle_button)
        videoView = findViewById(R.id.videoView)
        videoToggle = findViewById(R.id.video_toggle_button)
        seekBar = findViewById(R.id.seekBar)
        textProgress = findViewById(R.id.textView2)
        totalTime = findViewById(R.id.textView)
        radio2Toggle = findViewById(R.id.radio_toggle_button2)
        radio3Toggle = findViewById(R.id.radio_toggle_button3)

        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.holder.addCallback(this)
        videoView.setMediaController(mediaController)

        videoView.holder.addCallback(this)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnPreparedListener(this)
        seekBar.setOnSeekBarChangeListener(this)

        radioToggle.setOnClickListener{
            val value = mediaServices.getInt()
            message.what = 1
            mediaServices.radioToggle("http://stream.whus.org:8000/whusfm")
            mediaServices.SelectMedia(1)
            mediaServices.sendMessagge(message,1)
            Toast.makeText(applicationContext,"Hello: $value", Toast.LENGTH_SHORT).show()
        }

        radio2Toggle.setOnClickListener{
            val value = mediaServices.getInt()
            mediaServices.radioToggle("http://www.radioeins.de/livemp3")
            Toast.makeText(applicationContext,"Hello: $value", Toast.LENGTH_SHORT).show()
        }

        radio3Toggle.setOnClickListener{
            val value = mediaServices.getInt()
            mediaServices.radioToggle("https://kmojfm.streamguys1.com/live-mp3")
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

    private fun timeInString(seconds: Int): String {
        return String.format(
            "%02d:%02d",
            (seconds / 3600 * 60 + ((seconds % 3600) / 60)),
            (seconds % 60)
        )
    }

    @SuppressLint("SetTextI18n")
    private fun initializeSeekBar() {
        seekBar.max = mediaPlayer.seconds
        textProgress.text = "00:00"
        totalTime.text = timeInString(mediaPlayer.seconds)
    }

    private fun updateSeekBar() {
        runnable = Runnable {
            textProgress.text = timeInString(mediaPlayer.currentSeconds)
            seekBar.progress = mediaPlayer.currentSeconds
            handler.postDelayed(runnable, SECOND.toLong())
        }
        handler.postDelayed(runnable, SECOND.toLong())
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

    override fun onPrepared(mediaPlayer: MediaPlayer?) {
        initializeSeekBar()
        updateSeekBar()
        mediaPlayer?.start()
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

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
        mediaPlayer.release()
        mediaPlayer.releaseDrm()
    }

    override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser)
            mediaPlayer.seekTo(progress * SECOND)
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        TODO("Not yet implemented")
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        TODO("Not yet implemented")
    }

    private val MediaPlayer.seconds: Int
        get() {
            return this.duration / SECOND
        }

    private val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition / SECOND
        }
}