package com.selflearning.chemistree.activities.trening;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.selflearning.chemistree.R;

public class TrainingLoginActivity extends AppCompatActivity {

    private TrainingLoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trening_login);

        final TextInputLayout usernameInputLayout = findViewById(R.id.treningusernameInputLayout);
        final TextInputLayout passwordInputLayout = findViewById(R.id.treningpasswordInputLayout);
        final TextInputEditText usernameEditText = findViewById(R.id.treningusernameEditText);
        final TextInputEditText passwordEditText = findViewById(R.id.treningpasswordEditText);
        final Button loginButton = findViewById(R.id.treninglogin);
        final ProgressBar loadingProgressBar = findViewById(R.id.treningloading);

        loginViewModel = ViewModelProviders
                .of(this, new TrainingLoginViewModelFactory())
                .get(TrainingLoginViewModel.class);

        loginViewModel.getTrainingLoginFromState().observe(this, new Observer<TrainingLoginFromState>() {
            @Override
            public void onChanged(TrainingLoginFromState trainingLoginFromState) {
                if(trainingLoginFromState == null){
                    return;
                }
                loginButton.setEnabled(trainingLoginFromState.isDataValid());
                if(trainingLoginFromState.getUsernameError() != null){
                    usernameInputLayout.setError(getString(trainingLoginFromState.getUsernameError()));
                }
                if(trainingLoginFromState.getPasswordError() != null){
                    passwordInputLayout.setError(getString(trainingLoginFromState.getPasswordError()));
                }
            }
        });

        TextWatcher afterChangingText = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged (
                        usernameEditText.getText().toString(),
                        passwordEditText.getText().toString()
                );
            }
        };

        usernameEditText.addTextChangedListener(afterChangingText);
        passwordEditText.addTextChangedListener(afterChangingText);


    }
}
