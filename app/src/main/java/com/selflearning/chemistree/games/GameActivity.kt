package com.selflearning.chemistree.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.selflearning.chemistree.MenuDialogFragment
import com.selflearning.chemistree.R
import com.selflearning.chemistree.games.new_approach.GameFragment
import com.selflearning.chemistree.utils.ActivityUtilities
import com.selflearning.chemistree.utils.AppConstants

class GameActivity : BaseGameActivity(), GameInterface {

    private var dialogFragment: MenuDialogFragment? = null

    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(GameObserver(this))
        val intent = intent
        type = intent.getIntExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, -1)
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.gameContainer, fragment)
                .commit()
        createDialog()
    }

    private val fragment: Fragment
        get() = when (type) {
//            0 -> GameFragment3.getInstance(this)
//            1 -> GameFragment4.getInstance(this)
//            2 -> GameFragment2.getInstance(this)
//            3 -> GameFragment1.getInstance(this)
            else -> GameFragment.newInstance()
        }

    private fun createDialog() {
        dialogFragment = MenuDialogFragment(this, type)
    }

    override fun onBackPressed() {
        dialogFragment!!.show(supportFragmentManager, MenuDialogFragment.TAG)
    }

    override fun finishGame(score: Long) {
        ActivityUtilities.invokePostGameActivity(this, GameScoreActivity::class.java, type, score, true)
    }

    override fun observerMenuDialog(dialogFragmentShow: OnDialogFragmentShow) {
        dialogFragment!!.setDialogFragmentShow(dialogFragmentShow)
    }
}