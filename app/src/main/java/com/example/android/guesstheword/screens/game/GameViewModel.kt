package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        Log.i(this.javaClass.name, "onCleared")
    }
}