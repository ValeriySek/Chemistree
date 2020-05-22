package com.selflearning.chemistree.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.selflearning.chemistree.constants.AppConstants;

public class AppPreference {

    private static Context mContext;

    private static AppPreference appPreference = null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor mEditor;

    public static AppPreference getInstance(Context context){
        if(appPreference == null){
            mContext = context;
            appPreference = new AppPreference();
        }
        return appPreference;
    }

    public AppPreference() {
        sharedPreferences = mContext.getSharedPreferences(AppConstants.APP_PREFERENCE_NAME, mContext.MODE_PRIVATE);
        this.mEditor = sharedPreferences.edit();
    }

    public void setScoreResult(String name, int score){
        String scoreStr = Integer.toString(score);
        mEditor.putString(name, scoreStr);
        mEditor.apply();
    }
}
