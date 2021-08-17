package com.selflearning.chemistree.utilities

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import com.selflearning.chemistree.games.models.PreGameModel
import java.util.*

object ActivityUtilities {

    fun invokeNewActivity(
        activity: Activity,
        tClass: Class<*>?,
        typeFr: Int,
        shouldFinish: Boolean,
        list: PreGameModel? = null
    ) {
        val intent = Intent(activity, tClass)
        intent.putExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, typeFr)
//        intent.putExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, list)
        activity.startActivity(intent)
        if (shouldFinish) {
            activity.finish()
        }
    }

    fun invokePostGameActivity(
        activity: Activity,
        tClass: Class<*>?,
        typeFr: Int,
        score: Long,
        shouldFinish: Boolean
    ) {
        val intent = Intent(activity, tClass)
        intent.putExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, typeFr)
        intent.putExtra(AppConstants.BUNDLE_KEY_INDEX_SCORE, score)
        activity.startActivity(intent)
        if (shouldFinish) {
            activity.finish()
        }
    }

    fun invokeCommonAppActivity(activity: Activity, tClass: Class<*>?, score: Long, list: ArrayList<out Parcelable?>?, shouldFinish: Boolean) {
        val intent = Intent(activity, tClass)
        intent.putExtra(AppConstants.BUNDLE_KEY_INDEX_SCORE, score)
        intent.putParcelableArrayListExtra(AppConstants.BUNDLE_KEY_REPORT_MODEL, list)
        activity.startActivity(intent)
        if (shouldFinish) {
            activity.finish()
        }
    }

//    companion object {
//        private var activityUtilities: ActivityUtilities? = null
//        @JvmStatic
//        val instance: ActivityUtilities?
//            get() {
//                if (activityUtilities == null) {
//                    activityUtilities = ActivityUtilities()
//                }
//                return activityUtilities
//            }
//    }
}