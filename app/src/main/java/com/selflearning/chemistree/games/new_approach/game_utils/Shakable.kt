package com.selflearning.chemistree.games.new_approach.game_utils

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.BounceInterpolator

interface Shakable {

    fun View.shake(duration: Long = 200) {
//        animate()
//            .rotation()

        ObjectAnimator.ofFloat(this, View.ROTATION, 10f, 0f).apply {
            this.duration = duration
            interpolator = BounceInterpolator()
            start()
        }
//        ValueAnimator.ofFloat(20f, 0f).apply {
//            this.duration = duration
//            interpolator = BounceInterpolator()
//            addUpdateListener {
//                rotation = it.animatedValue as Float
//            }
//            start()
//        }
    }
}