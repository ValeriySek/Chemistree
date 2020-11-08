package com.selflearning.chemistree.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.selflearning.chemistree.activities.MainActivity;
import com.selflearning.chemistree.R;

public class LoginActivity0 extends AppCompatActivity {

    private EditText mLogin;
    private EditText mPassword;
    private Button buttonEnter;
    private Button buttonRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        mLogin = findViewById(R.id.etLogin);
        mPassword = findViewById(R.id.etPassword);
        buttonEnter = findViewById(R.id.buttonEnter);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonEnter.setOnClickListener(mOnEnterClickListener);
        buttonRegister.setOnClickListener(mOnRegisterClickListener);


    }

    private View.OnClickListener mOnEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isEmailValid() && isPasswordValid()){

            }
        }
    };

    private View.OnClickListener mOnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isEmailValid() && isPasswordValid()){
                String email = mLogin.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
//                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("TAG", "createUserWithEmail:success");
//                            finish();
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(LoginActivity0.this, "Authentication failed." + task.getException(),
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(LoginActivity0.this, MainActivity.class));
                            Log.d("TAG", "loginUserWithEmail:success");
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity0.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    };

    private boolean isEmailValid(){
        boolean isEmpty = TextUtils.isEmpty(mLogin.getText());
        boolean isEmail = Patterns.EMAIL_ADDRESS.matcher(mLogin.getText()).matches();
        if(isEmpty) {
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }
        if(!isEmail) {
            Toast.makeText(this, "Not Email", Toast.LENGTH_SHORT).show();
        }
        return !isEmpty &&
                isEmail;
    }

    private boolean isPasswordValid(){
        return !TextUtils.isEmpty(mPassword.getText());
    }
}
