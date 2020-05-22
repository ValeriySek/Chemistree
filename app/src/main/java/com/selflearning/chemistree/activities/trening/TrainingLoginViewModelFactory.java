package com.selflearning.chemistree.activities.trening;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TrainingLoginViewModelFactory implements ViewModelProvider.Factory {


    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(TrainingLoginViewModel.class)){
            return (T) new TrainingLoginViewModel();
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
