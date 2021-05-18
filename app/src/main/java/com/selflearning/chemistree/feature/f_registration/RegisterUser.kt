package com.selflearning.chemistree.feature.f_registration

import android.content.Intent

interface RegisterUser {

    fun register(data: Intent?)
    fun setListener(onRegister: OnSuccessRegistration)
    fun getIntent(): Intent
    fun revokeAccess()
}