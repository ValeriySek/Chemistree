package com.selflearning.chemistree.games.game4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.selflearning.chemistree.chemistry.elements.ElementRepository;
import com.selflearning.chemistree.chemistry.inorganic.acids.Acids;
import selflearning.chemistree.domain.chemistry.elements.Element;
import com.selflearning.chemistree.games.FormulaTransformations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameFragment4ViewModel {

    private MutableLiveData<List<String>> mutableStringsRV = new MutableLiveData<>();
    private MutableLiveData<List<Boolean>> mutableBooleansRV = new MutableLiveData<>();
    private MutableLiveData<String> stringQuestion = new MutableLiveData<>();
    private MutableLiveData<String> editTextAnswer = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDeleteButtonEnable = new MutableLiveData<>();
    private MutableLiveData<String> score = new MutableLiveData<>();

    private final int COUNT_OF_BUTTONS = 8;

    private List<Acids> mAcidsList;
    private List<Element> mElementList;

    private List<String> mListForRV = new ArrayList<>();
    private List<String> mStackList = new ArrayList<>();
    private List<Boolean> mIsButtonsEnableList = new ArrayList<>();
    private List<Integer> mIntegerList = new ArrayList<>();

    private String stringAnswer = "";
    private String mFormula;
    private long mTimeStart;
    private long scoreL;

    private Random random = new Random();

    GameFragment4ViewModel(@NonNull Application application) {
        ElementRepository repository = new ElementRepository(application);
//        acidsList = repository.getAcidsList();
        mAcidsList = repository.getAcidsByLvl(3);
//        mElementList = repository.getAllElements();
        loadData();
    }

    public void loadData(){

        mTimeStart = System.currentTimeMillis();

        mStackList.clear();
        mListForRV.clear();
        mIsButtonsEnableList.clear();

        refreshText();

        int randomAcid = random.nextInt(mAcidsList.size());
        mFormula = mAcidsList.get(randomAcid).getFormula();
        List<String> stringList = FormulaTransformations.disassembleFormula(mFormula);

        String question = mAcidsList.get(randomAcid).getName();
        mListForRV.addAll(stringList);

        for(int i = 0; i < COUNT_OF_BUTTONS; i++){
            mIsButtonsEnableList.add(true);
        }

        for(int i = mListForRV.size(); i < COUNT_OF_BUTTONS; i++){
            int randomElement = (int) (Math.random() * mElementList.size());
            mListForRV.add(mElementList.get(randomElement).getSymbol());
        }

        Collections.shuffle(mListForRV);

        mutableStringsRV.setValue(mListForRV);
        mutableBooleansRV.setValue(mIsButtonsEnableList);
        stringQuestion.setValue(question);
    }



    void changeEnable(int position, String s){
        mIsButtonsEnableList.remove(position);
        mStackList.add(s);
        mIsButtonsEnableList.add(position, false);
        mIntegerList.add(position);

        mutableBooleansRV.setValue(mIsButtonsEnableList);
        refreshText();
    }



    void deleteLast(){
        if(mStackList.size() != 0) {
            mStackList.remove(mStackList.size() - 1);
            int i = mIntegerList.get(mIntegerList.size()-1);
            mIsButtonsEnableList.remove(i);
            mIsButtonsEnableList.add(i, true);
            mIntegerList.remove(mIntegerList.size()-1);
            mutableBooleansRV.setValue(mIsButtonsEnableList);
            refreshText();
        }
    }



    private void refreshText(){
        stringAnswer = "";
        for(String h : mStackList){
            stringAnswer += h;
        }
        editTextAnswer.setValue(stringAnswer);
        if(stringAnswer.equals("")){
            isDeleteButtonEnable.setValue(false);
        }else {
            isDeleteButtonEnable.setValue(true);
        }
    }



    void setAllEnabled(){
        mIsButtonsEnableList.clear();
        for(int i = 0; i < COUNT_OF_BUTTONS; i++){
            mIsButtonsEnableList.add(false);
        }
        mutableBooleansRV.setValue(mIsButtonsEnableList);
    }


    long setScore(){
        long timeFinish = System.currentTimeMillis() - mTimeStart;
        scoreL += 1000000 / timeFinish;
        score.setValue(String.valueOf(scoreL));

        return scoreL;
    }


    LiveData<List<String>> getMutableStringsRV() {
        return mutableStringsRV;
    }

    LiveData<List<Boolean>> getMutableBooleansRV() {
        return mutableBooleansRV;
    }

    LiveData<String> getStringQuestion() {
        return stringQuestion;
    }

    LiveData<String> getEditTextAnswer() {
        return editTextAnswer;
    }

    LiveData<Boolean> isDeleteButtonEnable() {
        return isDeleteButtonEnable;
    }

    public LiveData<String> getScore() {
        return score;
    }

    public String getFormula() {
        return mFormula;
    }

}
