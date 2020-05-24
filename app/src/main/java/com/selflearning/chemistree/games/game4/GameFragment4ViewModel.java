package com.selflearning.chemistree.games.game4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.selflearning.chemistree.chemistry.elements.AppDatabase;
import com.selflearning.chemistree.chemistry.elements.ElementRepository;
import com.selflearning.chemistree.chemistry.inorganic.acids.Acids;
import com.selflearning.chemistree.dBHelper.DatabaseAccess;
import com.selflearning.chemistree.chemistry.elements.Element;
import com.selflearning.chemistree.games.FormulaTransformations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameFragment4ViewModel {

    private MutableLiveData<List<String>> mutableStringsRV = new MutableLiveData<>();
    private MutableLiveData<List<Boolean>> mutableBooleansRV = new MutableLiveData<>();
    private MutableLiveData<String> stringQuestion = new MutableLiveData<>();
    private MutableLiveData<String> editTextAnswer = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDeleteButtonEnable = new MutableLiveData<>();
    private MutableLiveData<String> score = new MutableLiveData<>();

    private final int COUNT_OF_BUTTONS = 8;

    private List<Acids> acidsList;
    private List<Element> elementList;

    private List<String> listForRV = new ArrayList<>();
    private List<String> stackList = new ArrayList<>();
    private List<Boolean> isButtonsEnableList = new ArrayList<>();
    private List<Integer> integerList = new ArrayList<>();

    private String stringAnswer = "";
    private String formula;
    private long timeStart;
    private long scoreL;

    private ElementRepository repository;

    public GameFragment4ViewModel(@NonNull Application application) {
        repository = new ElementRepository(application);
        acidsList = repository.getAcidsList();
        elementList = repository.getAllElements();
        loadData();
    }

    public void loadData(){

        timeStart = System.currentTimeMillis();

        stackList.clear();
        listForRV.clear();
        isButtonsEnableList.clear();

        refreshText();

        int randomAcid = (int) (Math.random() * acidsList.size());
        formula = acidsList.get(randomAcid).getFormula();
        List<String> stringList = FormulaTransformations.disassembleFormula(formula);

        String question = acidsList.get(randomAcid).getName();
        listForRV.addAll(stringList);

        for(int i = 0; i < COUNT_OF_BUTTONS; i++){
            isButtonsEnableList.add(true);
        }

        for(int i = listForRV.size(); i < COUNT_OF_BUTTONS; i++){
            int randomElement = (int) (Math.random() * elementList.size());
            listForRV.add(elementList.get(randomElement).getSymbol());
        }

        Collections.shuffle(listForRV);

        mutableStringsRV.setValue(listForRV);
        mutableBooleansRV.setValue(isButtonsEnableList);
        stringQuestion.setValue(question);
    }



    public void changeEnable(int position, String s){
        isButtonsEnableList.remove(position);
        stackList.add(s);
        isButtonsEnableList.add(position, false);
        integerList.add(position);

        mutableBooleansRV.setValue(isButtonsEnableList);
        refreshText();
    }



    public void deleteLast(){
        if(stackList.size() != 0) {
            stackList.remove(stackList.size() - 1);
            int i = integerList.get(integerList.size()-1);
            isButtonsEnableList.remove(i);
            isButtonsEnableList.add(i, true);
            integerList.remove(integerList.size()-1);
            mutableBooleansRV.setValue(isButtonsEnableList);
            refreshText();
        }
    }



    private void refreshText(){
        stringAnswer = "";
        for(String h : stackList){
            stringAnswer += h;
        }
        editTextAnswer.setValue(stringAnswer);
        if(stringAnswer.equals("")){
            isDeleteButtonEnable.setValue(false);
        }else {
            isDeleteButtonEnable.setValue(true);
        }
    }



    public void setAllEnabled(){
        isButtonsEnableList.clear();
        for(int i = 0; i < COUNT_OF_BUTTONS; i++){
            isButtonsEnableList.add(false);
        }
        mutableBooleansRV.setValue(isButtonsEnableList);
    }


    public long setScore(){
        long timeFinish = System.currentTimeMillis() - timeStart;
        scoreL += 1000000 / timeFinish;
        score.setValue(String.valueOf(scoreL));

        return scoreL;
    }


    public LiveData<List<String>> getMutableStringsRV() {
        return mutableStringsRV;
    }

    public LiveData<List<Boolean>> getMutableBooleansRV() {
        return mutableBooleansRV;
    }

    public LiveData<String> getStringQuestion() {
        return stringQuestion;
    }

    public LiveData<String> getEditTextAnswer() {
        return editTextAnswer;
    }

    public LiveData<Boolean> isDeleteButtonEnable() {
        return isDeleteButtonEnable;
    }

    public LiveData<String> getScore() {
        return score;
    }

    public String getFormula() {
        return formula;
    }

}
