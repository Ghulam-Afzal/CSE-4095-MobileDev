package com.example.lab_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ScoreActivity : AppCompatActivity() {

    private val score: TextView
        get() = findViewById(R.id.score_text)

    var score_activity_label: TextView? = null

    private val buttonToMainActivity: Button
        get() = findViewById(R.id.button_to_main_activity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        score_activity_label = findViewById(R.id.score_activity_label)
        intent?.let {
            val myStr = it.getStringExtra("FROM_MAIN")
            score_activity_label?.setText("FINAL SCORE: $myStr")
        }

        buttonToMainActivity.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Intent(baseContext, MainActivity::class.java).also{
                        mainActivity ->
                    startActivity(mainActivity)
                }
            }
        })

    }
}