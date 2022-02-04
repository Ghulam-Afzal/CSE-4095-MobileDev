package com.example.lab_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lab_2.contollers.NextQuestion
import com.example.lab_2.model.AllQuestions
import android.widget.EditText

class MainActivity : AppCompatActivity() {


    // make and assigned vars to null with button boxing
    var trueButton: Button? = null
    var falseButton: Button? = null
    var nextButton: Button? = null
    var questionText: TextView? = null

    val nextQuestion: NextQuestion = NextQuestion()

    private val toScoreActivity: EditText
        get() = findViewById(R.id.to_score_activity)

    private val buttonToScoreActivity: Button
        get() = findViewById(R.id.button_to_ScoreActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent?.let{
            val myText = it.getStringExtra("FROM_SCORE")
            toScoreActivity.setText(myText)
        }


        buttonToScoreActivity.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {

                Intent(baseContext, ScoreActivity::class.java).also { scoreActivity ->
                    scoreActivity.putExtra("FROM_MAIN", toScoreActivity.getText().toString())
                    startActivity(scoreActivity)

                }
            }
        })

        // attach the vars to the correct item on the view using ids
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_question)
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

        nextButton?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val nextQuestionIndex = nextQuestion.linearNextQuestion()
                questionText?.setText(nextQuestionIndex)
                Toast.makeText(baseContext, "Clicked NEXT", Toast.LENGTH_SHORT).show()
            }
        })
    }
}