package com.example.lab_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lab_2.contollers.NextQuestion
import com.example.lab_2.model.AllQuestions
import com.example.lab_2.model.Score

class MainActivity : AppCompatActivity() {


    // make and assigned vars to null with button boxing
    var trueButton: Button? = null
    var falseButton: Button? = null
    var nextButton: Button? = null
    var questionText: TextView? = null
    var scoreText: TextView? = null
    var score: Int? = 0

    val nextQuestion: NextQuestion = NextQuestion()

//    private val toScoreActivity: EditText
//        get() = findViewById(R.id.to_score_activity)

    private val buttonToScoreActivity: Button
        get() = findViewById(R.id.button_to_ScoreActivity)

    var total_score: Score = Score()

    var questions: AllQuestions = AllQuestions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonToScoreActivity.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {

                Intent(baseContext, ScoreActivity::class.java).also { scoreActivity ->
                    scoreActivity.putExtra("FROM_MAIN", score.toString())
                    startActivity(scoreActivity)

                }
            }
        })

        // attach the vars to the correct item on the view using ids
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_question)
        questionText = findViewById(R.id.textView2)
        scoreText = findViewById((R.id.score_text))
        scoreText?.setText("Score: $score")

        // set onClick listeners for the buttons
        trueButton?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){
                if (questions.allQuestions[nextQuestion.getIndex()].isTrue){
                    score = total_score.inc()
                    scoreText?.setText("Score: $score")
                    Log.i("BUGGED", nextQuestion.getIndex().toString())
                    Log.i("BUGGEDSC", score.toString())
                    Toast.makeText(baseContext, "Correct", Toast.LENGTH_SHORT).show()
                }
                else {
                    score = total_score.dec()
                    scoreText?.setText("Score: $score")
                    Toast.makeText(baseContext, "Incorrect", Toast.LENGTH_SHORT).show()
                }
                val nextQuestionIndex = nextQuestion.linearNextQuestion()
                questionText?.setText(nextQuestionIndex)
            }
        })

        falseButton?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){
                if (questions.allQuestions[nextQuestion.getIndex()].isTrue){
                    score = total_score.dec()
                    scoreText?.setText("Score: $score")
                    Log.i("BUGGED", nextQuestion.getIndex().toString())
                    Log.i("BUGGEDSC", score.toString())
                    Toast.makeText(baseContext, "Incorrect", Toast.LENGTH_SHORT).show()
                }
                else {
                    score = total_score.inc()
                    scoreText?.setText("Score: $score")
                    Toast.makeText(baseContext, "Correct", Toast.LENGTH_SHORT).show()
                }
                val nextQuestionIndex = nextQuestion.linearNextQuestion()
                questionText?.setText(nextQuestionIndex)
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