package com.selflearning.chemistree.feature.f_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selflearning.chemistree.chemistry.elements.AppDatabase
import com.selflearning.chemistree.chemistry.elements.Element
import com.selflearning.chemistree.chemistry.inorganic.acids.Acid
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases
import com.selflearning.chemistree.chemistry.inorganic.oxides.Oxides
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val database: AppDatabase
) : ViewModel() {

    private val mText: MutableLiveData<String>
    val text: LiveData<String>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is home fragment"
    }

    fun getBases(): LiveData<List<Bases>> {
        return database.basesDao().getAll()
    }

//    fun getAcids(): LiveData<List<Acid>> {
//        return database.acidsDao().getAll()
//    }

    fun getOxides(): LiveData<List<Oxides>> {
        return database.oxidesDao().getAll()
    }

//    fun getElements(): LiveData<List<Element>> {
//        return database.elementDao().getAll()
//    }

    fun getData() {



        suspend {

        }
    }
}