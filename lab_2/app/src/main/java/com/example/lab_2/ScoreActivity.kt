package com.example.lab_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class ScoreActivity : AppCompatActivity() {

    private val toMainActivity: EditText
        get() = findViewById(R.id.to_main_activity)

    private val buttonToMainActivity: Button
        get() = findViewById(R.id.button_to_main_activity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)



        intent?.let {
            val myStr = it.getStringExtra("FROM_MAIN")
            toMainActivity.setText(myStr)
        }

        buttonToMainActivity.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Intent(baseContext,MainActivity::class.java).also{
                        mainActivity ->
                    mainActivity.putExtra("FROM_SCORE",toMainActivity.getText().toString())
                    startActivity(mainActivity)
                }
            }
        })

    }
}