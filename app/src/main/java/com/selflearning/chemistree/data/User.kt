package com.selflearning.chemistree.data

import com.google.firebase.auth.FirebaseAuth

class User private constructor() {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firstName: String?
        get() = mAuth.currentUser?.displayName
    val uid: String
        get() = mAuth.currentUser?.uid ?: ""
    var secondName: String? = null
    val email: String?
        get() = mAuth.currentUser?.email
    var profileImg = 0
    fun deleteUser() {
        user = null
    }

    companion object {
        private var user: User? = null
        @JvmStatic
        val instance: User?
            get() {
                if (user == null) {
                    user = User()
                }
                return user
            }
    }

}