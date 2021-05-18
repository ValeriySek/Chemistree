package com.selflearning.chemistree.feature.f_registration

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
        private val registerUser: RegisterUser
): ViewModel() {

    var signInIntent: MutableLiveData<Intent> = MutableLiveData()

    fun setListener(onSuccessRegistration: OnSuccessRegistration){
        registerUser.revokeAccess()
        registerUser.setListener(onSuccessRegistration)
    }
    fun register(data: Intent?) {
        registerUser.register(data)
    }

    fun signIn() {
        signInIntent.value = registerUser.getIntent()
    }
}