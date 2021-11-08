package com.selflearning.chemistree.games.game2;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.selflearning.chemistree.domain.chemistry.inorganic.acids.Acid;

import java.util.ArrayList;
import java.util.List;

public class Game_2_ViewModel{

    private final String TAG = this.getClass().getSimpleName();

    private MutableLiveData<List<String>> mutableLiveData;
    private MutableLiveData<String> stringQuestion;
    private final int COUNT_OF_BUTTONS = 4;
    private List<Acid> acidsList;
    private List<String> stringList;
    private List<Acid> acids;
    public int rightAnswer;


    public Game_2_ViewModel(@NonNull Application application) {
//        acids = new ElementRepository(application).getAcidsList();
        mutableLiveData = new MutableLiveData<>();
        stringQuestion = new MutableLiveData<>();
        acidsList = new ArrayList<>();
        stringList = new ArrayList<>();
        loadData();
    }


    public void loadData(){
        acidsList.clear();
        stringList.clear();
        for(int i = 0; i < COUNT_OF_BUTTONS; ){
            int j = (int)(Math.random() * acids.size());
            if(!acidsList.contains(acids.get(j))){
                acidsList.add(acids.get(j));
                stringList.add(acids.get(j).getFormula());
                i++;
            } else {
                Log.i(TAG, "acidsList contain " + i + " " + j);
            }
        }
        rightAnswer = (int)(Math.random() * COUNT_OF_BUTTONS);
        mutableLiveData.setValue(stringList);
        stringQuestion.setValue(acidsList.get(rightAnswer).getName());
    }

    public LiveData<List<String>> getAnswers(){
        return mutableLiveData;
    }

    public LiveData<String> getStringQuestion() {
        return stringQuestion;
    }

}
