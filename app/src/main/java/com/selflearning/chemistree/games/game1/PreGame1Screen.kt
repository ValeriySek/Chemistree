package com.selflearning.chemistree.games.game1

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.selflearning.chemistree.R
import com.selflearning.chemistree.databinding.ActivityPreGame1ScreenBinding
import com.selflearning.chemistree.utilities.ActivityUtilities

class PreGame1Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPreGame1ScreenBinding.inflate(layoutInflater)
        initVar()
        initView(binding)
        initListener(binding)
    }

    private fun initVar() {
    }

    private fun initView(binding: ActivityPreGame1ScreenBinding) {
        setContentView(binding.root)
        loadImage(binding)
    }

    private fun initListener(binding: ActivityPreGame1ScreenBinding) {
        binding.bPlayGame.setOnClickListener {
            ActivityUtilities.invokeNewActivity(this, Game_1_Activity::class.java, -1, true)
        }
    }

    private fun loadImage(binding: ActivityPreGame1ScreenBinding) {
        val d = this.resources.getDrawable(R.drawable.ic_benzol)
        binding.imageView2.setImageDrawable(d)

    }
}