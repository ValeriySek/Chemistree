package com.selflearning.chemistree.games

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.selflearning.chemistree.R
import com.selflearning.chemistree.constants.AppConstants
import com.selflearning.chemistree.databinding.ActivityGameScoreBinding
import com.selflearning.chemistree.databinding.ActivityGamesBinding
import com.selflearning.chemistree.utilities.ActivityUtilities

class GameScoreActivity : BaseGameActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        val i = intent.getIntExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, -1)
        val score = intent.getLongExtra(AppConstants.BUNDLE_KEY_INDEX_SCORE, 0)
        binding.tvPostGameScore.text = score.toString()
        val components = GameComponentsList()
        val gameComponents = components.gameComponentsList[i]
        binding.clPostGame.background = getDrawable(gameComponents.back)
        binding.clPostGame.setOnClickListener(View.OnClickListener {
            ActivityUtilities.invokeNewActivity(this@GameScoreActivity, PostGameActivity::class.java, -1, true)
        })
    }

    override fun onBackPressed() {}
}