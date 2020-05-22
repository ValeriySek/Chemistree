package com.selflearning.chemistree.games.game1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.utilities.ActivityUtilities;

public class PreGame_1_Screen extends AppCompatActivity {

    Activity activity;
    Context context;

    private Button bPlayGame;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVar();
        initView();
        initListener();
    }

    private void initVar(){
        activity = PreGame_1_Screen.this;
        context = activity.getApplicationContext();
    }

    private void initView(){
        setContentView(R.layout.activity_pre_game_1__screen);

        bPlayGame = findViewById(R.id.bPlayGame);
        imageView = findViewById(R.id.imageView2);
        loadImage();
    }

    private void initListener(){
        bPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtilities.getInstance().invokeNewActivity(activity, Game_1_Activity.class, -1, true);
            }
        });
    }

    private void loadImage(){
        Drawable d = activity.getResources().getDrawable(R.drawable.ic_benzol);
        imageView.setImageDrawable(d);

//        Picasso picasso = new Picasso.Builder(context).listener(new Picasso.Listener() {
//            @Override
//            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
//
//            }
//        }).build();
//
//            picasso.load(R.drawable.ic_benzol)
//                    .error(R.drawable.mendeleev)
//                    .into(imageView);

//        try {
//            InputStream is = getAssets().open("img/ic_benzol.xml");
//            Drawable d = Drawable.createFromStream(is, null);
//            imageView.setImageDrawable(d);
//        } catch (IOException e) {
//            return;
//        }
    }
}
