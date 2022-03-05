package com.selflearning.chemistree.games.new_approach

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.selflearning.chemistree.di.view_model.di.ViewModelFactory

class GameViewModelWithActionsFactory(
    private val gameRepository: GameRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModelWithActions::class.java)) {
            return GameViewModelWithActions(gameRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}