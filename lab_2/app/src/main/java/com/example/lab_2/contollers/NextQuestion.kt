package com.example.lab_2.contollers

import com.example.lab_2.model.AllQuestions

class NextQuestion {

    private val allQestions: AllQuestions = AllQuestions()

    private var question: Int = 0
    private val total_qs: Int = allQestions.allQuestions.size


    public fun linearNextQuestion(): Int {
        question = (question + 1) % total_qs
        return allQestions.allQuestions[question].index
    }

    public fun pseudoRandomNextQuestion(): Int {
        question = (question + 1) % total_qs
        return allQestions.allQuestions[question].index
    }

}