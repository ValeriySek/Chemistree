package com.selflearning.chemistree.activities

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
import com.selflearning.chemistree.R
import com.selflearning.chemistree.activities.RegisterActivity
import com.selflearning.chemistree.login.LoginActivity0

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerEmail: MaterialButton
    private lateinit var registerGoogle: SignInButton
    private lateinit var alreadyHaveAcc: TextView
    private var mGoogleSignInClient: GoogleSignInClient? = null

    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerEmail = findViewById(R.id.registerEmail)
        alreadyHaveAcc = findViewById(R.id.registerTVIHaveAcc)
        registerGoogle = findViewById(R.id.registerGoogle)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        if (mGoogleSignInClient != null) {
            mGoogleSignInClient!!.revokeAccess().addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "mGoogleSignInClient.revokeAccess", Toast.LENGTH_SHORT).show()
                }
            }
        }
        mAuth = FirebaseAuth.getInstance()
        registerEmail.setOnClickListener(View.OnClickListener { })
        alreadyHaveAcc.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity0::class.java))
            finish()
        }
        registerGoogle.setOnClickListener { signIn() }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
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
        mAuth!!.signInWithCredential(credential)
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