package com.selflearning.chemistree.feature.f_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
        private val unregisterUser: UnregisterUser
) : ViewModel() {

    private val mText: MutableLiveData<String>
    val text: LiveData<String>
        get() = mText

    fun signOut() {
        unregisterUser.signOut()
    }

    init {
        mText = MutableLiveData()
        mText.value = "This is notifications fragment"
    }
}