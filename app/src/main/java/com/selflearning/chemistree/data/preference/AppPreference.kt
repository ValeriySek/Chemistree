package com.selflearning.chemistree.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.selflearning.chemistree.constants.AppConstants

class AppPreference {
    private val sharedPreferences: SharedPreferences
    private val mEditor: SharedPreferences.Editor

    fun setScoreResult(name: String?, score: Int) {
        val scoreStr = Integer.toString(score)
        mEditor.putString(name, scoreStr)
        mEditor.apply()
    }

    companion object {
        private var mContext: Context? = null
        private var appPreference: AppPreference? = null
        fun getInstance(context: Context?): AppPreference? {
            if (appPreference == null) {
                mContext = context
                appPreference = AppPreference()
            }
            return appPreference
        }
    }

    init {
        sharedPreferences = mContext!!.getSharedPreferences(AppConstants.APP_PREFERENCE_NAME, Context.MODE_PRIVATE)
        mEditor = sharedPreferences.edit()
    }
}