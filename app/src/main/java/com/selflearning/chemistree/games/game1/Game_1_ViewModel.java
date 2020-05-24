package com.selflearning.chemistree.games.game1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.selflearning.chemistree.chemistry.elements.ElementRepository;
import com.selflearning.chemistree.chemistry.inorganic.acids.Acids;

import java.util.ArrayList;
import java.util.List;

class Game_1_ViewModel {

    private MutableLiveData<List<String>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> stringQuestion = new MutableLiveData<>();

    private List<Acids> acids;
    private List<Acids> acidsTempList;
    private List<String> strings;

    int rightAnswer;

    Game_1_ViewModel(@NonNull Application application){

        acids = new ElementRepository(application).getAcidsList();

        acidsTempList = new ArrayList<>();
        strings = new ArrayList<>();

        loadData();
    }

    void loadData(){
        final int COUNT_OF_BUTTONS = 3;

        acidsTempList.clear();
        strings.clear();

        for(int i = 0; i < COUNT_OF_BUTTONS; ){
            int j = (int)(Math.random() * acids.size());
            if(!acidsTempList.contains(acids.get(j))){
                acidsTempList.add(acids.get(j));
                strings.add(acids.get(j).getFormula());
                i++;
            }
        }

        rightAnswer = (int)(Math.random() * COUNT_OF_BUTTONS);

        mutableLiveData.setValue(strings);
        stringQuestion.setValue(acidsTempList.get(rightAnswer).getName());
    }

    LiveData<List<String>> getAnswers() {
        return mutableLiveData;
    }

    LiveData<String> getStringQuestion() {
        return stringQuestion;
    }
}
