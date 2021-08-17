package com.selflearning.chemistree.games

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.selflearning.chemistree.chemistry.elements.Repository
import com.selflearning.chemistree.games.game4.Game4ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class PreGameViewModel(
    val repository: Repository
) : ViewModel() {


    private val _uiState = MutableStateFlow<Game4ViewModel.ViewState>(Game4ViewModel.ViewState.Loading)
    var uiState: StateFlow<Game4ViewModel.ViewState> = _uiState


    fun loadElements() = viewModelScope.launch {
        val f1 = repository.getAllElements()
//            .collect {
//            _uiState.value = Game4ViewModel.ViewState.Success(it)
//        }
        val f2 = repository.getAllAcids()
//            .collect {
//            _uiState.value = Game4ViewModel.ViewState.Success(it)
//        }
        _uiState.value = Game4ViewModel.ViewState.Success(listOf())
        f1.combine(f2) { list, list2 ->
            delay(1500)
            Log.i("TAGG", "Game4ViewModel  list   ${list}")
            Log.i("TAGG", "Game4ViewModel  list   ${list2}")
//            _uiState.value = Game4ViewModel.ViewState.Success(list)
//            _uiState.value = Game4ViewModel.ViewState.Success(list2)
            list + list2
        }
            .collect {
            if(!it.isNullOrEmpty()) {
                _uiState.value = Game4ViewModel.ViewState.Success(it)
            }
        }
    }
}