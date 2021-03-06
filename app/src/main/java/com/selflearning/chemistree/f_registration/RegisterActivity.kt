package com.selflearning.chemistree.f_registration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.selflearning.chemistree.ChemistreeApplication
import com.selflearning.chemistree.R
import com.selflearning.chemistree.activities.MainActivity
import com.selflearning.chemistree.login.LoginActivity0
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerEmail: MaterialButton
    private lateinit var registerGoogle: SignInButton
    private lateinit var alreadyHaveAcc: TextView

    @Inject lateinit var auth: FirebaseAuth
    @Inject lateinit var gso: GoogleSignInOptions
    @Inject lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as ChemistreeApplication).appComponentFactory.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerEmail = findViewById(R.id.registerEmail)
        alreadyHaveAcc = findViewById(R.id.registerTVIHaveAcc)
        registerGoogle = findViewById(R.id.registerGoogle)

        googleSignInClient.revokeAccess().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@RegisterActivity, "mGoogleSignInClient.revokeAccess", Toast.LENGTH_SHORT).show()
            }
        }

        registerEmail.setOnClickListener { }
        alreadyHaveAcc.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity0::class.java))
            finish()
        }
        registerGoogle.setOnClickListener { signIn() }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                account?.let { firebaseAuthWithGoogle(it) }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.id)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithCredential:success")
                        Toast.makeText(this@RegisterActivity, "" + acct.email, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithCredential:failure", task.exception)
                    }
                }
    }

    companion object {
        private const val RC_SIGN_IN = 100
    }
}