package com.selflearning.chemistree.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.auth.FirebaseAuth
import com.selflearning.chemistree.f_registration.RegisterActivity
import com.selflearning.chemistree.data.User
import com.selflearning.chemistree.databinding.ActivityLaunchBinding

class LaunchActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(bind.root)
        mAuth = FirebaseAuth.getInstance()
        user = User.instance
        val handler = Handler()
        val av = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext)
        Log.i("TAGG", "isGooglePlayServicesAvailable $av")
        handler.postDelayed(
                {
                    if (mAuth!!.currentUser != null) {
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        startActivity(Intent(this, RegisterActivity::class.java))
                    }
                    finish()
                },
                2100)
    }
}