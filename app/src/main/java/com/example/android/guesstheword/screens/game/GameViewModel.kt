package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    public val word = MutableLiveData<String>()
    public val score = MutableLiveData<Int>()
    private lateinit var wordList: MutableList<String>

    init {
        score.value = 0
        resetList()
        nextWord()
        Log.i("GameViewModel","GameViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(this.javaClass.name, "onCleared")
    }

    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (wordList.isEmpty()) {
//            gameFinished()
        } else {
            word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        score.value = (score.value ?: 0) - 1
        nextWord()
    }

    fun onCorrect() {
        score.value = (score.value ?: 0) + 1
        nextWord()
    }
}