package com.example.k2022_04_22_lab7

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {

    private lateinit var getDataButton: Button
    private lateinit var getImageButton: Button
    private lateinit var basicQuestionView: TextView
    private lateinit var imageView: ImageView

    val urlJSON = "http://192.168.56.1:8080/questions";
    val urlIMAGE = "http://192.168.56.1:8080/static/stamford_harbor.jpg";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataButton = findViewById(R.id.get_data_button)
        getImageButton = findViewById(R.id.image_button)
        basicQuestionView = findViewById(R.id.basic_question_view)
        imageView = findViewById(R.id.imageView)

        getDataButton.setOnClickListener {

// ...

// Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)


            val jsonArrayRequest = JsonArrayRequest(Request.Method.GET,
                urlJSON,
                null,
                { response ->
                    // Display the first 500 characters of the response string.
                    basicQuestionView.setText("Response is: ${response}")
                },
                { error ->  basicQuestionView.text = "Error: ${error}" })

            queue.add(jsonArrayRequest)
        }

        getImageButton.setOnClickListener {

            val queue = Volley.newRequestQueue(this)
            val imageRequest = ImageRequest(
                urlIMAGE,
                { response: Bitmap ->
                    // Display the first 500 characters of the response string.
                    imageView.setImageBitmap(response)
                },
                0,0,
                ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,

                { error ->  basicQuestionView.text = "Error: ${error}" })

            queue.add(imageRequest)
        }

        }

    }

