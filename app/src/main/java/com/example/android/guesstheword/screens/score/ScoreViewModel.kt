package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(score: Int) : ViewModel() {
    private val _score = MutableLiveData<Int>()
    public  val score: LiveData<Int>
        get() = _score

    init {
        _score.value = score
    }
}