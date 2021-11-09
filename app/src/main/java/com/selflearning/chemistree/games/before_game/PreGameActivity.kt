package com.selflearning.chemistree.games.before_game

import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.selflearning.chemistree.ChemistreeApplication
import com.selflearning.chemistree.utilities.AppConstants
import com.selflearning.chemistree.databinding.ActivityPreGameBinding
import com.selflearning.chemistree.di.view_model.di.ViewModelFactory
import com.selflearning.chemistree.games.BaseGameActivity
import com.selflearning.chemistree.games.GameActivity
import com.selflearning.chemistree.games.GameComponentsList
import com.selflearning.chemistree.games.models.PreGameModel
import com.selflearning.chemistree.utilities.ActivityUtilities
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class PreGameActivity : BaseGameActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: PreGameViewModel

    private lateinit var list: PreGameModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ChemistreeApplication).appComponentFactory.inject(this)

        val binding = ActivityPreGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory)[PreGameViewModel::class.java]

        val type = intent.getIntExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, -1)





        val components = GameComponentsList()
        val gameComponents = components.gameComponentsList[type]
        val drawable = ContextCompat.getDrawable(this, gameComponents.back)

        binding.preGameLayout.background = drawable
        binding.preGameText.text = gameComponents.nameOfGame

        viewModel.loadElements()
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when(it) {
                    is PreGameViewModel.ViewState.Loading -> {
                        binding.preGameButton.isEnabled = false
                    }
                    is PreGameViewModel.ViewState.Success -> {
                        Log.i("TAGG", "PreGameActivity  list   ${it.preGameModel}")
                        binding.preGameButton.isEnabled = true
                    }
                }
            }
        }
        binding.preGameButton.setOnClickListener {
            ActivityUtilities.invokeNewActivity(
                this@PreGameActivity,
                GameActivity::class.java,
                    type, true)
        }
    }
}