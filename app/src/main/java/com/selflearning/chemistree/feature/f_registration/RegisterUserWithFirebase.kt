package com.selflearning.chemistree.feature.f_registration

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

class RegisterUserWithFirebase @Inject constructor(
        private val auth: FirebaseAuth,
        private val context: Context,
        private val googleSignInClient: GoogleSignInClient
) : RegisterUser {

    private lateinit var onRegister: OnSuccessRegistration

    override fun register(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            // Google Sign In was successful, authenticate with Firebase
            val account = task.getResult(ApiException::class.java)
            account?.let { firebaseAuthWithGoogle(it) }
        } catch (e: ApiException) {
            // Google Sign In failed, update UI appropriately
            Log.i("TAG", "Google sign in failed", e)
        }
    }

    override fun setListener(onRegister: OnSuccessRegistration) {
        this.onRegister = onRegister
    }

    override fun getIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    override fun revokeAccess() {
        googleSignInClient.revokeAccess().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onRegister.onRevokeAccess()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.i("TAG", "firebaseAuthWithGoogle:" + acct.id)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.i("TAG", "signInWithCredential:success")
                        onRegister.onSuccess()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.i("TAG", "signInWithCredential:failure ", task.exception)
                    }
                }
    }
}