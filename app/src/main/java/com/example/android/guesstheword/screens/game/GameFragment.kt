/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {
    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val viewModel:GameViewModel by activityViewModels()

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        binding.gameViewModel = viewModel

        viewModel.currentTime.observe(viewLifecycleOwner, Observer { newValue ->
            binding.timerText.text = newValue
        })

        viewModel.word.observe(viewLifecycleOwner, Observer { newValue ->
            binding.wordIsText.text = newValue.toString()
        })

        viewModel.score.observe(viewLifecycleOwner, Observer { newValue ->
            binding.scoreText.text = newValue.toString()
        })

        viewModel.isGameFinished.observe(viewLifecycleOwner, Observer { newValue ->
            if (!newValue) return@Observer
            gameFinished()
            viewModel.resetGameState()
        })

        return binding.root
    }

    private fun gameFinished() {
        val score = binding.gameViewModel!!.score.value ?: 0
        val action = GameFragmentDirections.actionGameToScore(score)
        this.findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("GameFragment", "onDestroy")
    }
}
