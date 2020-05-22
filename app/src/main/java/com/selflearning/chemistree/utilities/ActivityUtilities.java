package com.selflearning.chemistree.utilities;

import android.app.Activity;
import android.content.Intent;

import com.selflearning.chemistree.constants.AppConstants;
import com.selflearning.chemistree.data.User;

import java.util.ArrayList;

public class ActivityUtilities {

    User user = User.getInstance();

    private static ActivityUtilities activityUtilities = null;
    public static ActivityUtilities getInstance(){
        if(activityUtilities == null){
            activityUtilities = new ActivityUtilities();
        }
        return activityUtilities;
    }

    public void invokeNewActivity(Activity activity, Class<?> tClass, int typeFr, boolean shouldFinish){
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, typeFr);
        activity.startActivity(intent);
        if(shouldFinish){
            activity.finish();
        }
    }


    public void invokePostGameActivity(Activity activity, Class<?> tClass, int typeFr, long score, boolean shouldFinish){
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, typeFr);
        intent.putExtra(AppConstants.BUNDLE_KEY_INDEX_SCORE, score);
        activity.startActivity(intent);
        if(shouldFinish){
            activity.finish();
        }
    }

    public void invokeCommonAppActivity(Activity activity, Class<?> tClass, long score, ArrayList<? extends android.os.Parcelable> list, boolean shouldFinish){
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(AppConstants.BUNDLE_KEY_INDEX_SCORE, score);
        intent.putParcelableArrayListExtra(AppConstants.BUNDLE_KEY_REPORT_MODEL, list);
        activity.startActivity(intent);
        if (shouldFinish){
            activity.finish();
        }
    }


}
