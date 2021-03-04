package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    companion object {
        const val DONE = 0L
        const val ONE_SECOND = 1000L
        const val COUNTDOWN_TIME = 10000L
    }

    private val _currentTime = MutableLiveData<Long>()
    public  val currentTime: LiveData<Long>
        get() = _currentTime

    public val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    private val timer: CountDownTimer

    private val _word = MutableLiveData<String>()
    public val word: LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    public val score: LiveData<Int>
        get() = _score

    private val _isGameFinished = MutableLiveData<Boolean>()
    public val isGameFinished: LiveData<Boolean>
        get() = _isGameFinished

    private val _isWordGuessed = MutableLiveData<Boolean>()
    public  val isWordGuessed: LiveData<Boolean>
        get() = _isWordGuessed

    private val _isWordSkipped = MutableLiveData<Boolean> ()
    public val isWordSkipped: LiveData<Boolean>
        get() = _isWordSkipped

    private lateinit var wordList: MutableList<String>

    init {
        _isGameFinished.value = false
        _score.value = 0

        resetList()
        nextWord()

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                _isGameFinished.value = true
            }
        }
        timer.start()
        Log.i("GameViewModel","GameViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
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
        if (wordList.isEmpty()) resetList()
        _word.value = wordList.removeAt(0)
    }

    fun onSkip() {
        _isWordSkipped.value = true
        _score.value = (score.value ?: 0) - 1
        nextWord()
    }

    fun onCorrect() {
        _isWordGuessed.value = true
        _score.value = (score.value ?: 0) + 1
        nextWord()
    }

    fun resetGameState() {
        _isGameFinished.value = false
    }
}