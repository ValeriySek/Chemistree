package com.selflearning.chemistree

import android.app.Activity
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.selflearning.chemistree.games.OnDialogFragmentShow
import com.selflearning.chemistree.games.before_game.PreGameActivity
import com.selflearning.chemistree.utilities.ActivityUtilities
import java.util.*

class MenuDialogFragment(private val activity: Activity, private val type: Int) : DialogFragment(), View.OnClickListener {
    private var dialogFragmentShow: OnDialogFragmentShow? = null

    interface MyDialogListener {
        fun isDialogShown(isShow: Boolean)
    }

    fun setDialogFragmentShow(dialogFragmentShow: OnDialogFragmentShow?) {
        this.dialogFragmentShow = dialogFragmentShow
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.dialog_pause_game, container, false)
        Objects.requireNonNull(Objects.requireNonNull(dialog)?.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Log.i("GameFr", "created")

        dialogFragmentShow!!.isDialogFragmentShown(true)
        v.findViewById<View>(R.id.buttonMenu).setOnClickListener(this)
        v.findViewById<View>(R.id.buttonResume).setOnClickListener(this)
        v.findViewById<View>(R.id.buttonRestart).setOnClickListener(this)
        return v
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonMenu -> onMenuClicked(v)
            R.id.buttonResume -> onResumeClicked(v)
            R.id.buttonRestart -> onRestartClicked(v)
        }
    }

    private fun onMenuClicked(v: View) {
        dismiss()
        activity.finish()
    }

    private fun onResumeClicked(v: View) {
        dismiss()
    }

    private fun onRestartClicked(v: View) {
        dismiss()
        ActivityUtilities.invokeNewActivity(activity, PreGameActivity::class.java, type, true)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        //        dialogListener.isDialogShown(false);
        dialogFragmentShow!!.isDialogFragmentShown(false)
        Log.i("GameFr", "dismissed")
    }

    companion object {
        const val TAG = "MenuDialog"
    }
}