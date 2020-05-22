package com.selflearning.chemistree.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.selflearning.chemistree.MainActivity;
import com.selflearning.chemistree.R;
import com.selflearning.chemistree.data.User;


public class LaunchActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mAuth = FirebaseAuth.getInstance();
        user = User.getInstance();

        if(mAuth.getCurrentUser() != null){
                startActivity(new Intent(this, MainActivity.class));
                finish();
        } else {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        }

    }

}
