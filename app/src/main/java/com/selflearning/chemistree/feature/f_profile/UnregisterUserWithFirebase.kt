package com.selflearning.chemistree.feature.f_profile

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class UnregisterUserWithFirebase @Inject constructor(
        private val auth: FirebaseAuth
) : UnregisterUser {

    override fun signOut() {
        auth.signOut()
    }
}