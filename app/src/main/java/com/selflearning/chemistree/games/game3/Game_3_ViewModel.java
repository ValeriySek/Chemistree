package com.selflearning.chemistree.games.game3;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.selflearning.chemistree.chemistry.elements.AppDatabase;
import com.selflearning.chemistree.chemistry.elements.ElementRepository;
import com.selflearning.chemistree.dBHelper.DatabaseAccess;
import com.selflearning.chemistree.chemistry.elements.Element;

import java.util.ArrayList;
import java.util.List;

public class Game_3_ViewModel {

    private final String TAG = this.getClass().getSimpleName();
    private final int COUNT_OF_BUTTONS = 8;

    private MutableLiveData<List<String>> mutableLiveData;
    private MutableLiveData<List<Boolean>> booleanLD;

    private MutableLiveData<String> stringQuestion;
    private MutableLiveData<String> stringAns;

    private List<Element> elementList;
    private List<Element> elements;

    private List<String> stringList;
    private List<String> stackList;
    private List<Boolean> booleanList;
    private List<Integer> integerList;

    public int rightAnswer;
    private String stringAnswer = "";

    public Game_3_ViewModel(@NonNull Application application) {
        elements = new ElementRepository(application).getAllElements();
        mutableLiveData = new MutableLiveData<>();
        booleanLD = new MutableLiveData<>();
        stringQuestion = new MutableLiveData<>();
        stringAns = new MutableLiveData<>();
        elementList = new ArrayList<>();
        stringList = new ArrayList<>();
        stackList = new ArrayList<>();
        booleanList = new ArrayList<>();
        integerList = new ArrayList<>();
        loadData();
    }

    void loadData(){
        elementList.clear();
        stringList.clear();
        for(int i = 0; i < COUNT_OF_BUTTONS; ){
            booleanList.add(true);
            int j = (int)(Math.random() * elements.size());
            if(!elementList.contains(elements.get(j))){
                elementList.add(elements.get(j));
                stringList.add(elements.get(j).getSymbol());
                i++;
            } else {
                Log.i(TAG, "acidsList contain " + i + " " + j);
            }
        }
        booleanLD.setValue(booleanList);
        rightAnswer = (int)(Math.random() * COUNT_OF_BUTTONS);
        mutableLiveData.setValue(stringList);
        stringQuestion.setValue(elementList.get(rightAnswer).getTitle());
    }

    public LiveData<List<String>> getAnswers(){
        return mutableLiveData;
    }

    public LiveData<String> getStringQuestion() {
        return stringQuestion;
    }

    public MutableLiveData<List<Boolean>> getBooleanLD() {
        return booleanLD;
    }

    public void chandgeEnable(int position, String s){
        booleanList.remove(position);
        stackList.add(s);
        booleanList.add(position, false);
        integerList.add(position);
        booleanLD.setValue(booleanList);
        refreshText();
    }

    public void deleteLast(){
        if(stackList.size() != 0) {
            stackList.remove(stackList.size() - 1);
            int i = integerList.get(integerList.size()-1);
            booleanList.remove(i);
            booleanList.add(i, true);
            integerList.remove(integerList.size()-1);
            booleanLD.setValue(booleanList);
            refreshText();
        }
    }

    private void refreshText(){
        stringAnswer = "";
        for(String h : stackList){
            stringAnswer += h;
        }
        stringAns.setValue(stringAnswer);
    }

    public LiveData<String> getStringAns() {
        return stringAns;
    }
}
