package com.selflearning.chemistree.games.before_game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selflearning.chemistree.domain.chemistry.elements.Repository
import com.selflearning.chemistree.games.models.GameQuestion
import com.selflearning.chemistree.games.models.PreGameModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class PreGameViewModel(
    val repository: Repository
) : ViewModel() {


    private val _uiState = MutableStateFlow<ViewState>(ViewState.Loading)
    var uiState: StateFlow<ViewState> = _uiState


    fun loadElements() = viewModelScope.launch {
        val f1 = repository.getAllElements()
        val f2 = repository.getAllAcids()
        f1.combine(f2) { list, list2 ->
            delay(1500)
            val gameQuestion = list2.shuffled().map {
                GameQuestion(it.name, it.formula)
            }.filterIndexed { index, _ ->
                index < 10
            }
            val auxiliaryList = list.map {
                it.symbol
            }
            PreGameModel(gameQuestion, auxiliaryList)
        }
            .collect {
                _uiState.value = ViewState.Success(it)
        }
    }



    sealed class ViewState {
        object Loading : ViewState()
        data class Success(var preGameModel: PreGameModel) : ViewState()
    }
}