package com.example.k2022_04_22_lab7.models.questions

class AllQuestions {
    private val allQuestions = mutableListOf<Question>()

    fun numberOfQuestions() : Int {
        return allQuestions.size
    }


}