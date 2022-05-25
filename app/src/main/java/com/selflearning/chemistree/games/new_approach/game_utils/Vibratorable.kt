package com.selflearning.chemistree.games.new_approach.game_utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.fragment.app.Fragment

interface Vibratorable {

    fun Fragment.vibrate() {
        val vibrator = vibrator(requireContext())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(100)
        }
    }


    private fun vibrator(context: Context): Vibrator {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (context
                .getSystemService(
                    Context.VIBRATOR_MANAGER_SERVICE
                ) as VibratorManager)
                .defaultVibrator
        }
        else
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
}