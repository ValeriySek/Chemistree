package com.selflearning.chemistree.games;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.constants.AppConstants;
import com.selflearning.chemistree.games.game4.GameFragment2;
import com.selflearning.chemistree.games.game5.GameFragment1;
import com.selflearning.chemistree.utilities.ActivityUtilities;

public class GameActivity extends BaseGameActivity implements GameInterface{


    private Dialog dialog;


    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        type = intent.getIntExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, -1);
//        getLifecycle().addObserver(new GameObserver(this));

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.gameContainer, getFragment())
                .commit();

        createDialog();
    }

    private Fragment getFragment() {
        switch (type){
            case 0 :
             return GameFragment1.getInstance();
            case 1 :
             return GameFragment2.getInstance(this);
             default:
             return null;
        }
    }

    private void createDialog(){
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x - 30;
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_pause_game);
        Button buttonMenu = dialog.findViewById(R.id.buttonMenu);
        Button buttonResume = dialog.findViewById(R.id.buttonResume);
        Button buttonRestart = dialog.findViewById(R.id.buttonRestart);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                finish();
            }
        });
        buttonResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                ActivityUtilities.getInstance().invokeNewActivity(GameActivity.this, PreGameActivity.class,
                        type, true);
            }
        });

        dialog.getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onBackPressed() {
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "Activity destroyed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG", "Activity onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG", "Activity onStop");
    }

    @Override
    public void finishGame(long score) {
        ActivityUtilities.getInstance().invokePostGameActivity(this, GameScoreActivity.class, type, score, true);
    }

}
