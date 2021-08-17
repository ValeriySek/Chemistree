package com.selflearning.chemistree.games.game1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.selflearning.chemistree.chemistry.inorganic.acids.Acid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Game_1_ViewModel {

    private MutableLiveData<List<String>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> stringQuestion = new MutableLiveData<>();
    private MutableLiveData<Long> mutableLiveDataScore = new MutableLiveData<>();

    private Random random = new Random();

    private List<Acid> acids;
    private List<Acid> acidsTempList;
    private List<String> strings;

    int rightAnswer;
    private long timeStart;
    private long score = 0;

    Game_1_ViewModel(@NonNull Application application){

//        acids = new ElementRepository(application).getAcidsList();

        acidsTempList = new ArrayList<>();
        strings = new ArrayList<>();

        loadData();
    }

    void loadData(){
        timeStart = System.currentTimeMillis();

        final int COUNT_OF_BUTTONS = 3;

        acidsTempList.clear();
        strings.clear();

        for(int i = 0; i < COUNT_OF_BUTTONS; ){
            int j = random.nextInt(acids.size());
            if(!acidsTempList.contains(acids.get(j))){
                acidsTempList.add(acids.get(j));
                strings.add(acids.get(j).getFormula());
                i++;
            }
        }

        rightAnswer = random.nextInt(COUNT_OF_BUTTONS);

        mutableLiveData.setValue(strings);
        stringQuestion.setValue(acidsTempList.get(rightAnswer).getName());
    }


    void increaseScore(){
        long timeFinish = System.currentTimeMillis() - timeStart;
        score += 1000000 / timeFinish;
        mutableLiveDataScore.setValue(score);
    }

    public LiveData<Long> getMutableLiveDataScore() {
        return mutableLiveDataScore;
    }

    LiveData<List<String>> getAnswers() {
        return mutableLiveData;
    }

    LiveData<String> getStringQuestion() {
        return stringQuestion;
    }
}
