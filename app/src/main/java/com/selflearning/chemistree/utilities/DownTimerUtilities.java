package com.selflearning.chemistree.utilities;

import android.os.CountDownTimer;
import android.widget.ProgressBar;

public class DownTimerUtilities extends CountDownTimer {

    private long millisInFuture;
    private ProgressBar progressBar;
    private boolean ifFinifh;
    Class aClass;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public DownTimerUtilities(long millisInFuture, long countDownInterval, ProgressBar progressBar, boolean ifFinifh, Class<?> tClass) {
        super(millisInFuture, countDownInterval);
        this.progressBar = progressBar;
        this.millisInFuture = millisInFuture;
        this.ifFinifh = ifFinifh;
        aClass = tClass;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        long progress =  millisUntilFinished * 100 / millisInFuture;
        progressBar.setProgress((int)progress);
    }

    @Override
    public void onFinish() {
        ifFinifh = true;
    }

    public boolean isIfFinifh() {
        return ifFinifh;
    }
}
