package com.selflearning.chemistree.games.game5

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.selflearning.chemistree.databinding.FragmentGame1Binding
import com.selflearning.chemistree.games.GameScoreActivity
import com.selflearning.chemistree.utils.ActivityUtilities

class GameFragment5 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentGame1Binding.inflate(inflater, container, false)
        initListener(binding)
        return binding.root
    }

    private fun initListener(binding: FragmentGame1Binding) {
        binding.clFragment1.setOnClickListener {
            activity?.parent?.let { it1 ->
                ActivityUtilities.invokeNewActivity(it1, GameScoreActivity::class.java, TYPE, true)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAG", "Fragment destroyed")
    }

    companion object {
        private const val TYPE = 0
        val instance: GameFragment5
            get() = GameFragment5()
    }
}