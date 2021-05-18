package com.selflearning.chemistree.games.game1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.selflearning.chemistree.chemistry.inorganic.acids.Acids;
import com.selflearning.chemistree.constants.AppConstants;
import com.selflearning.chemistree.dBHelper.DatabaseAccess;
import com.selflearning.chemistree.R;
import com.selflearning.chemistree.models.ReportModels;
import com.selflearning.chemistree.utilities.ActivityUtilities;
import com.selflearning.chemistree.utilities.MyDialogFragment;
import com.selflearning.chemistree.utilities.SoundUtilities;
import com.selflearning.chemistree.utilities.SoundsBox;

import java.util.ArrayList;
import java.util.List;

public class Game_1_Activity extends AppCompatActivity {

    private Activity activity;

    private TextView textView;
    private TextView textView3;
    private TextView tvDialogQ;
    private TextView tvDialogTip;
    private ProgressBar progressBar;
    private LinearLayout llDialogTip;

    private SoundsBox soundsBox;
    private Vibrator vibrator;
    private Dialog dialog;
    private FragmentTransaction ft;
    private final AnimatorSet animatorSet = new AnimatorSet();
    private Interpolator interpolator;

    private List<SoundUtilities> soundsList;
    private List<Acids> acidsList;
    private ArrayList<ReportModels> reportModels = new ArrayList<>();

    private CountDownTimer countDownTimer;
    private final long MILLIS_IN_FUTURE = 15000;
    private long mTimeLeft = MILLIS_IN_FUTURE;
    private long countDownInterval = 100;
    private long time;
    private long currentTime;
    private boolean isTimerPaused = false;
    private boolean isDialogActive = false;
    private boolean isDialogWasCalled = false;

    private int acidsListLength;
    private int rightNumberPosition;
    private int rightAcidNumber;
    private long score = 0;
    private boolean isFinish = false;


    private String chosenName;
    private String correctName;
    private String chosenDesc;
    private String correctDesc;
    private String mGivenAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        initView();
//        initVar();
//        invokeDialogTip();
//        initListener();
//        playNext();
    }

    private void initView(){
        setContentView(R.layout.activity_game_1);
        textView = findViewById(R.id.game_1_textView);
        progressBar = findViewById(R.id.stats_progressbar);
        progressBar.setProgress(100);
        textView3 = findViewById(R.id.tvScore);
        textView3.setText(String.format("%s", score));
    }

    private void initVar(){
        activity = Game_1_Activity.this;

//        acidsList = DatabaseAccess.getInstance(this).getAllAcids();
        acidsListLength = acidsList.size();

        soundsBox = new SoundsBox(getBaseContext());
        soundsList = soundsBox.getmSoundsList();

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_tip);
        tvDialogQ = dialog.findViewById(R.id.tvDialogQ);
        tvDialogTip = dialog.findViewById(R.id.tvDialogTip);
        llDialogTip = dialog.findViewById(R.id.llDialogTip);
        llDialogTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDialogActive = false;
                dialog.dismiss();
                reQuestion();
            }
        });
    }

//    private void initListener(){
//        View.OnClickListener onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onButtonClick(v);
//            }
//        };
////        for(Button b : buttons){
////            b.setOnClickListener(onClickListener);
//        }
//    }

//    private void playNext(){
//        textView.setVisibility(View.VISIBLE);

//        time = System.currentTimeMillis();

//        rightAcidNumber = (int) (Math.random() * acidsListLength);
//        Acids acid = acidsList.get(rightAcidNumber);

//        pickForReport(acid);

//        acidFormula = acid.getAcidFormulaForTv();
//        acidName = acid.getAcidRuName();

//        tvDialogQ.setText(Html.fromHtml(acidFormula));
//        tvDialogTip.setText(Html.fromHtml(acidName));
//        textView.setText(Html.fromHtml(acidFormula));

//        rightNumberPosition = (int) (Math.random() * buttons.size());

//        for(int i = 0; i < buttons.size(); i++){
//            if(i == rightNumberPosition){
//                buttons.get(i).setText(acid.getAcidRuName());
//            }else{
//                String wrong = generateWrongAnswer();
//                buttons.get(i).setText(wrong);
//            }
//        }
//    }



    private int generateWrongNum(){
        return (int)(Math.random() * acidsListLength);
    }

    private void onButtonClick(View v){
        Button button = (Button) v;
        String tag = button.getTag().toString();

        if (Integer.parseInt(tag) == rightNumberPosition) {
            isDialogWasCalled = false;
            soundsBox.play(soundsList.get(AppConstants.BUNDLE_KEY_ZERO_INDEX));
            currentTime = System.currentTimeMillis();
            long t = currentTime - time;
            increaseScore(t);

            button.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.holo_green_light));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int cx = textView.getWidth() / 2;
                int cy = textView.getHeight() / 2;
                float radius = textView.getWidth();
                Animator anim = ViewAnimationUtils
                        .createCircularReveal(textView, cx, cy, radius, 0);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        textView.setVisibility(View.INVISIBLE);
                    }
                });
                anim.start();
            } else {
                textView.setVisibility(View.INVISIBLE);
            }

        } else {
            soundsBox.play(soundsList.get(AppConstants.BUNDLE_KEY_TWO_INDEX));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(200);
            }
            button.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.holo_red_light));
//            buttons.get(rightNumberPosition).setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.holo_green_light));
            mGivenAns = button.getText().toString();
            showDialog();
        }
        if(!isDialogActive){
            reQuestion();
        }
    }

    private void reQuestion(){
        long ms = 500;
        if(isDialogWasCalled){
            ms = 0;
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                for(int i = 0; i < buttons.size(); i++) {
//                    buttons.get(i).setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.colorAccent));
//                }
//                playNext();
            }
        }, ms);
    }

    private void downTimer(){
        countDownTimer = new CountDownTimer(mTimeLeft, countDownInterval){
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = millisUntilFinished;
                updateProgressTimer();
            }
            @Override
            public void onFinish() {
                progressBar.setProgress(0);
//                finishGame();
            }
        }.start();
    }


    private void updateProgressTimer(){
        long progress =  mTimeLeft * 100 / MILLIS_IN_FUTURE;
        progressBar.setProgress((int)progress);
    }

    private void increaseScore(long time){
        score += 1000000 / time;
        textView3.setText(String.format("%s", score));
    }

    private void pickForReport(Acids acid){
//        correctName = acid.getAcidFormulaForTv();
//        correctDesc = acid.getAcidRuName();
        chosenName = " ";
        chosenDesc = " ";
        reportModels.add(new ReportModels(chosenName, correctName, chosenDesc, correctDesc));
    }

    @Override
    protected void onPause() {
        super.onPause();
        isTimerPaused = true;
        Log.d("Pause", "pause");
        countDownTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isTimerPaused) {
            downTimer();
        }
        Log.d("Pause", "resume");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            downTimer();
        }
        if(!hasFocus){
            countDownTimer.cancel();
        }
    }


    private void showDialog(){

        dialog.show();
        isDialogActive = true;
        isTimerPaused = true;
        isDialogWasCalled = true;
        countDownTimer.cancel();
    }

    private void invokeDialogTip(){

        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int height = point.y / 2;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.6f;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, height);
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

//    private void finishGame(){
//        ActivityUtilities.getInstance().invokeCommonAppActivity(activity, PostGame_1_Screen.class, score, reportModels, true);
//    }
}
