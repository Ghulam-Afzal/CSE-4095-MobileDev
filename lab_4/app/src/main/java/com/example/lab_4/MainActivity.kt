package com.example.lab_4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    // array of object ids that can be fetched from the api
    private val arrayOfObjectIds = arrayOf(251120, 45734, 251139, 11417, 437869, 49177, 39637, 468634, 39666, 258449, 22634)
    // uri with out the object ids
    private val incompleteUri = "https://collectionapi.metmuseum.org/public/collection/v1/objects/"

    private val textView: TextView
        get () = findViewById(R.id.textView)

    private val httpButton: Button
        get () = findViewById(R.id.httpbutton)

    private val httpImage: ImageView
        get() = findViewById(R.id.imageView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        httpImage.setImageResource(R.drawable.yowokrmymans)
        httpButton.setOnClickListener {
            // add a random object id to the uri
            val randomItemFromArray = arrayOfObjectIds.random().toString()
            val completedUri = " $incompleteUri$randomItemFromArray"

            // use the completed uri to call the getText Function
            println(completedUri)
            getText(completedUri)
        }

    }

    // yo yo we in here making http requests
    @SuppressLint("SetTextI18n")
    private fun getText(url: String) {
        val queue = Volley.newRequestQueue((this))
        val response = StringRequest(Request.Method.GET, url,
            { response ->
                // turn the response into a json object
                val obj = JSONObject(response)
                println(obj) // print for purpose of checking what was returned
                textView.text = "Department is ${obj.get("department")}; Title of the picture is ${obj.get("title")}"
                Toast.makeText(baseContext, "Name: ${obj.get("title")}", Toast.LENGTH_LONG).show()
                getImage(obj.get("primaryImage").toString())
            },
            { textView.text = "That didn't work!" })

        queue.add(response)
    }


   // this gets the image
    private fun getImage(url: String) {
        val queue = Volley.newRequestQueue((this))

        val imageRequest = ImageRequest(url,
            { imageRequest ->
                httpImage.setImageBitmap(imageRequest)
            },
            0, 0, ImageView.ScaleType.CENTER_CROP, null,
            { error ->
                Log.d("imageReqError", "request failed")
            }
            )
        queue.add(imageRequest)
    }
}