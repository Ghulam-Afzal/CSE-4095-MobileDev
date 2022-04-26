package com.example.k2022_04_22_lab7.models.questions

class Question(private var questionString: Int, private var isQuestionTrue: Boolean, private var imageFullName: String  = "") {
    private var question = questionString
    private var isTrue = isQuestionTrue
    private var imageName = imageFullName
}
