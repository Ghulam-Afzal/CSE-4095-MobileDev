package com.example.lab_2.model

class Score {

    private var score: Int = 0

    fun inc(): Int {
        score += 1
        return score
    }

    fun dec(): Int {
        score -= 1
        return score
    }

}