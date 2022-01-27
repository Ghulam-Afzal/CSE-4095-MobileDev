package com.example.lab_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    // make and assigned vars to null with button boxing
    var trueButton: Button? = null
    var falseButton: Button? = null
    var nextQuestion: Button? = null
    var questionText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // attach the vars to the correct item on the view using ids
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextQuestion = findViewById(R.id.next_question)
        questionText = findViewById(R.id.textView2)

        // set onClick listeners for the buttons 
        trueButton?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){
                Toast.makeText(baseContext, "Clicked True Button", Toast.LENGTH_SHORT).show()
            }
        })

        falseButton?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){
                Toast.makeText(baseContext, "Clicked False Button", Toast.LENGTH_SHORT).show()
            }
        })

        nextQuestion?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){
                Toast.makeText(baseContext, "Clicked Next Button", Toast.LENGTH_SHORT).show()
            }
        })
    }
}