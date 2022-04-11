package com.example.k2022_04_04c

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.widget.Button
import android.widget.Toast
import com.example.k2022_03_08_rv.model.RadioStations
import com.example.k2022_04_04c.Services.MediaServices

lateinit var mediaServices: MediaServices
private var bound: Boolean = false
private var message: Message = Message()

class MainActivity : AppCompatActivity() {

    lateinit var radioToggle: Button
    val radioStations: RadioStations = RadioStations()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioToggle = findViewById(R.id.radio_toggle_button)
        radioToggle.setOnClickListener{
            var value = mediaServices.getInt()
            message.what = 1
            mediaServices.radioToggle()
            mediaServices.SelectMedia(1)
            mediaServices.sendMessagge(message,1)
            Toast.makeText(applicationContext,"Hello: $value", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()

        val intent: Intent = Intent(this, MediaServices::class.java)
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


}