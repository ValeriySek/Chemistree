package com.selflearning.chemistree.games

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.material.button.MaterialButton
import com.selflearning.chemistree.R
import com.selflearning.chemistree.chemistry.elements.ElementRepository
import com.selflearning.chemistree.constants.AppConstants
import com.selflearning.chemistree.databinding.ActivityPostGameBinding
import com.selflearning.chemistree.databinding.ActivityPreGameBinding
import com.selflearning.chemistree.utilities.ActivityUtilities

class PreGameActivity : BaseGameActivity() {
    private var repository: ElementRepository? = null
    private var handler: Handler? = null
    private var isButtonEnabled = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPreGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = ElementRepository(application)
        handler = Handler()
        MyLiveData().booleanMutableLiveData.observe(this, { aBoolean ->
            binding.preGameButton.isEnabled = aBoolean!!
        })
        val intent = intent
        val type = intent.getIntExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, -1)
        val components = GameComponentsList()
        val gameComponents = components.gameComponentsList[type]
        val drawable = ContextCompat.getDrawable(this, gameComponents.back)
        binding.preGameLayout.background = drawable
        binding.preGameText.text = gameComponents.nameOfGame
        binding.preGameButton.setOnClickListener {
            ActivityUtilities.invokeNewActivity(this@PreGameActivity, GameActivity::class.java,
                    type, true)
        }
    }

    internal inner class MyLiveData {
        var booleanMutableLiveData = MutableLiveData<Boolean>()
        private fun waitH() {
            Log.i("Tag", "wait")
            handler!!.postDelayed({
//                isButtonEnabled = repository!!.allElements.size != 0
                booleanMutableLiveData.value = isButtonEnabled
                if (!isButtonEnabled) {
                    waitH()
                }
            }, 100)
        }

        init {
            if (!isButtonEnabled) {
                waitH()
            }
        }
    }
}