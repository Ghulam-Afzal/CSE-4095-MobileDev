package com.example.k2022_04_22_lab7.controllers

import java.util.*
import com.example.k2022_04_22_lab7.models.questions.AllQuestions


class NextQuestion {

    private val allQestions: AllQuestions = AllQuestions()

    private var question: Int = 0
    //private val total_qs: Int = allQestions.size


    public fun linearNextQuestion(): Int {
        //question = (question+1) % total_qs
        //return allQestions.allQuestions[question].index
        return 1
    }

    public fun pseudoRandomNextQuestion(): Int {
        val random = Random()
        //question = random.nextInt(total_qs)

        //return allQestions.allQuestions[question].index
        return 1
    }




}