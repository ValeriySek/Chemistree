package com.selflearning.chemistree.activities.trening;

import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.selflearning.chemistree.R;


public class TrainingLoginViewModel extends ViewModel {

    private MutableLiveData<TrainingLoginFromState> trainingLoginFromState = new MutableLiveData<>();

    LiveData<TrainingLoginFromState> getTrainingLoginFromState() {
        return trainingLoginFromState;
    }

    public void loginDataChanged(String username, String password){
        if(!isUserNameValid(username)){
            trainingLoginFromState.setValue(new TrainingLoginFromState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)){
            trainingLoginFromState.setValue(new TrainingLoginFromState(null, R.string.invalid_password));
        } else {
            trainingLoginFromState.setValue(new TrainingLoginFromState(true));
        }
    }

    private boolean isUserNameValid(String username){
        if(username == null){
            return false;
        }
        if(username.contains("@")){
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        }else {
            return !username.trim().isEmpty();
        }
    }

    private boolean isPasswordValid(String password){
        return password != null && password.trim().length() > 5;
    }
}
