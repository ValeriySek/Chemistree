package com.selflearning.chemistree.games;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.selflearning.chemistree.R;
import com.selflearning.chemistree.chemistry.elements.ElementRepository;
import com.selflearning.chemistree.constants.AppConstants;
import com.selflearning.chemistree.utilities.ActivityUtilities;

public class PreGameActivity extends BaseGameActivity {

    private ConstraintLayout constraintLayout;
    private TextView textView;
    private MaterialButton button;
    private ElementRepository repository;
    private Handler handler;
    private boolean isButtonEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = new ElementRepository(getApplication());
        setContentView(R.layout.activity_pre_game);
        constraintLayout = findViewById(R.id.preGameLayout);
        textView = findViewById(R.id.preGameText);
        button = findViewById(R.id.preGameButton);
        handler = new Handler();
        new MyLiveData().getBooleanMutableLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                button.setEnabled(aBoolean);
            }
        });

        Intent intent = getIntent();
        final int type = intent.getIntExtra(AppConstants.BUNDLE_KEY_TYPE_OF_FRAGMENT, -1);

        GameComponentsList components = new GameComponentsList();
        GameComponents gameComponents = components.getGameComponentsList().get(type);

        Drawable drawable = getDrawable(gameComponents.getBack());
        constraintLayout.setBackground(drawable);
        textView.setText(gameComponents.getNameOfGame());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtilities.getInstance().invokeNewActivity(PreGameActivity.this, GameActivity.class,
                        type, true);
            }
        });

    }



    class MyLiveData{
        MutableLiveData<Boolean> booleanMutableLiveData = new MutableLiveData<>();
        MyLiveData(){
            if(!isButtonEnabled){
                waitH();
            }
        }
        private void waitH(){
            Log.i("Tag" , "wait");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isButtonEnabled = repository.getAllElements().size() != 0;
                    booleanMutableLiveData.setValue(isButtonEnabled);
                    if(!isButtonEnabled){
                        waitH();
                    }
                }
            }, 100);
        }

        public MutableLiveData<Boolean> getBooleanMutableLiveData() {
            return booleanMutableLiveData;
        }
    }
}
