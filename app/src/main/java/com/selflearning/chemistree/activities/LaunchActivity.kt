package com.selflearning.chemistree.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.auth.FirebaseAuth
import com.selflearning.chemistree.ChemistreeApplication
import com.selflearning.chemistree.feature.f_registration.RegisterActivity
import com.selflearning.chemistree.data.User
import com.selflearning.chemistree.databinding.ActivityLaunchBinding
import javax.inject.Inject

class LaunchActivity : AppCompatActivity() {

    @Inject
    lateinit var auth: FirebaseAuth
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as ChemistreeApplication).appComponentFactory.inject(this)
        super.onCreate(savedInstanceState)
        val bind = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(bind.root)

        user = User.instance
        val handler = Handler()
        val av =
            GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext)
        Log.i("TAGG", "isGooglePlayServicesAvailable $av")
        handler.postDelayed({
            if (av != 0 || auth.currentUser != null) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
            finish()
        }, 1500)
    }
}