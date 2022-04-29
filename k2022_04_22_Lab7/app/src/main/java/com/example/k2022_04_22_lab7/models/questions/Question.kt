package com.example.k2022_04_22_lab7.models.questions

import android.util.Log

class Question {
    private var answers = mutableListOf<Answer>()
    private var question: String = ""
    private var imageName: String = ""

    override fun toString(): String {
        return question + " image " + imageName
    }

    fun numberOfAnswers() : Int {
        return answers.size
    }

    fun imageName() : String {
        return imageName
    }
}
