package com.selflearning.chemistree.f_mendeleev_table

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selflearning.chemistree.chemistry.elements.Element

class MTViewModel : ViewModel() {

    var mutableEl = MutableLiveData<List<Element?>>()

    fun getElements() {
        mutableEl.value = ElementsLongTable.elements
    }
}