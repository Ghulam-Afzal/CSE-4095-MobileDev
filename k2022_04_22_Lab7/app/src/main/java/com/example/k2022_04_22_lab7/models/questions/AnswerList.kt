package com.example.k2022_04_22_lab7.models.questions

class AnswerList {
     private var answerList = mutableListOf<Answer>()

    public fun numberOfAnswers() : Int {
        return answerList.size
    }

    fun addAnswer(answerString: String, isTrue: Boolean) {
        answerList.add(Answer(answerString,isTrue))
    }
}