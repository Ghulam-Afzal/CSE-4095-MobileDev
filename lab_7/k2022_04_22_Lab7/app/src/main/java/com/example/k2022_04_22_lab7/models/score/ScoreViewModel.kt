package com.example.k2022_04_22_lab7.models.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    private var _score: MutableLiveData<Int> = MutableLiveData(0)

    fun getScore() : LiveData<Int> {
        return _score
    }

    fun incremenet() {
        _score.value = _score.value?.plus(1)
    }

    fun decrement() {
        _score.value = _score.value?.minus(1)
    }

    fun zero() {
        _score.value = 0
    }

}