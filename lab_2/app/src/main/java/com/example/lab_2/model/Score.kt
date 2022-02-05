package com.example.lab_2.model

class Score {

    private var score: Int = 0

    fun inc(): Int {
        score += 5
        return score
    }

    fun dec(): Int {
        if (score > 0) {
            score -= 5
        }
        return score
    }

    fun skip_q(): Int {
        if (score > 0){
            score -= 10
        }
        return score
    }

    fun reset():Int {
        score = 0
        return score
    }
}