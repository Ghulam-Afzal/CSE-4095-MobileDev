package com.example.k2022_04_22_lab7.controllers

import com.example.k2022_04_22_lab7.models.questions.AllQuestions
import com.example.k2022_04_22_lab7.models.questions.Question
import com.google.gson.Gson

class LoadQuestion2Model(question: Question) {

    var gson: Gson = Gson()



    var allQuestions: AllQuestions = AllQuestions()

}