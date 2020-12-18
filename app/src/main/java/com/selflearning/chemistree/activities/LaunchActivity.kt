package com.selflearning.chemistree.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.selflearning.chemistree.R
import com.selflearning.chemistree.activities.RegisterActivity
import com.selflearning.chemistree.data.User

class LaunchActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        mAuth = FirebaseAuth.getInstance()
        user = User.instance
        if (mAuth!!.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        finish()
    }
}