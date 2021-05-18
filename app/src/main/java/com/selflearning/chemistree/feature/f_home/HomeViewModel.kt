package com.selflearning.chemistree.feature.f_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selflearning.chemistree.chemistry.elements.AppDatabase
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val appDatabase: AppDatabase
) : ViewModel() {

    private val mText: MutableLiveData<String>
    val text: LiveData<String>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is home fragment"
    }

    fun getBases(): LiveData<List<Bases>> {
        return appDatabase.basesDao().getAllBases()
    }

    fun getData() {



        suspend {

        }
    }
}