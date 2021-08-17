package com.selflearning.chemistree.games;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class GameObserver implements LifecycleObserver {

    private Activity activity;

    public GameObserver(Activity activity) {
        this.activity = activity;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void closeApp(){
        Log.i("TAG", "Lifecycle.Event.ON_STOP");
        activity.finish();
    }
}
