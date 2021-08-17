package com.selflearning.chemistree.games.game4

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selflearning.chemistree.chemistry.elements.Element
import com.selflearning.chemistree.chemistry.elements.Repository
import com.selflearning.chemistree.chemistry.inorganic.acids.Acid
import com.selflearning.chemistree.games.FormulaTransformations
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class Game4ViewModel(
    val repository: Repository
) : ViewModel() {

    private val mutableStringsRV = MutableLiveData<List<String>>()
    private val mutableBooleansRV = MutableLiveData<List<Boolean>>()
    private val stringQuestion = MutableLiveData<String>()
    private val editTextAnswer = MutableLiveData<String>()
    private val isDeleteButtonEnable = MutableLiveData<Boolean>()
    private val score = MutableLiveData<String>()


    private val COUNT_OF_BUTTONS = 8

    private var acidsList: List<Acid> = listOf()
    private var mElementList: List<Element> = listOf()
    private val mListForRV: MutableList<String> = ArrayList()
    private val mStackList: MutableList<String> = ArrayList()
    private val mIsButtonsEnableList: MutableList<Boolean> = ArrayList()
    private val mIntegerList: MutableList<Int> = ArrayList()
    private var stringAnswer = ""
    var formula: String = ""
    private var mTimeStart: Long = 0
    private var scoreL: Long = 0
    private val random = Random()



    sealed class ViewState {
        object Loading : ViewState()
        data class Success(var list: List<Any>) : ViewState()
    }



    private val _uiState = MutableStateFlow<ViewState>(ViewState.Loading)
    var uiState: StateFlow<ViewState> = _uiState



    //    fun loadAcids(): LiveData<List<Acid>> {
//        return repository.getAllAcids()
//    }
    fun loadElements() = viewModelScope.launch {
        val f1 = repository.getAllElements()
        val f2 = repository.getAllAcids()
        f1.combine(f2) { list, list2 ->
            delay(1500)
            list + list2
        }.collect {
            if(!it.isNullOrEmpty()) {
                _uiState.value = ViewState.Success(it)
            }
        }
    }

    fun setList(list: List<Acid>) {
        acidsList = list
    }

    fun setElements(list: List<Element>) {
        mElementList = list
        if (mElementList.isNotEmpty())
            loadData()
    }


    fun loadData() {
        mTimeStart = System.currentTimeMillis()

        mStackList.clear()
        mListForRV.clear()
        mIsButtonsEnableList.clear()

        refreshText()

        val randomAcid = random.nextInt(acidsList.size)

        formula = acidsList[randomAcid].formula
        val stringList = FormulaTransformations.disassembleFormula(
            formula
        )

        val question = acidsList[randomAcid].name
        mListForRV.addAll(stringList)

        for (i in 0 until COUNT_OF_BUTTONS) {
            mIsButtonsEnableList.add(true)
        }

        for (i in mListForRV.size until COUNT_OF_BUTTONS) {
            val randomElement = (Math.random() * mElementList.size).toInt()
            mElementList[randomElement].let { mListForRV.add(it.symbol) }
        }

        mListForRV.shuffle()
        mutableStringsRV.value = mListForRV
        mutableBooleansRV.value = mIsButtonsEnableList
        stringQuestion.value = question
    }

    fun changeEnable(position: Int, s: String) {
        mIsButtonsEnableList.removeAt(position)
        mStackList.add(s)
        mIsButtonsEnableList.add(position, false)
        mIntegerList.add(position)
        mutableBooleansRV.value = mIsButtonsEnableList
        refreshText()
    }

    fun deleteLast() {

        if (mStackList.size != 0) {
            mStackList.removeAt(mStackList.size - 1)
            val i = mIntegerList[mIntegerList.size - 1]
            mIsButtonsEnableList.removeAt(i)
            mIsButtonsEnableList.add(i, true)
            mIntegerList.removeAt(mIntegerList.size - 1)
            mutableBooleansRV.value = mIsButtonsEnableList
            refreshText()
        }
    }

    private fun refreshText() {
        stringAnswer = ""
        for (h in mStackList) {
            stringAnswer += h
        }
        editTextAnswer.value = stringAnswer
        if (stringAnswer == "") {
            isDeleteButtonEnable.setValue(false)
        } else {
            isDeleteButtonEnable.setValue(true)
        }
    }

    fun setAllEnabled() {
        mIsButtonsEnableList.clear()
        for (i in 0 until COUNT_OF_BUTTONS) {
            mIsButtonsEnableList.add(false)
        }
        mutableBooleansRV.value = mIsButtonsEnableList
    }

    fun setScore(): Long {
        val timeFinish = System.currentTimeMillis() - mTimeStart
        scoreL += 1000000 / timeFinish
        score.value = scoreL.toString()
        return scoreL
    }

    fun getMutableStringsRV(): LiveData<List<String>> {
        return mutableStringsRV
    }

    fun getMutableBooleansRV(): LiveData<List<Boolean>> {
        return mutableBooleansRV
    }

    fun getStringQuestion(): LiveData<String> {
        return stringQuestion
    }

    fun getEditTextAnswer(): LiveData<String> {
        return editTextAnswer
    }

    fun isDeleteButtonEnable(): LiveData<Boolean> {
        return isDeleteButtonEnable
    }

    fun getScore(): LiveData<String> {
        return score
    }
}