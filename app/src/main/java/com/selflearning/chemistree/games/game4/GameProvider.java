package com.selflearning.chemistree.games.game4;

import android.content.Context;

import com.selflearning.chemistree.chemistry.elements.Element;
import com.selflearning.chemistree.chemistry.inorganic.acids.Acids;
import com.selflearning.chemistree.dBHelper.DatabaseAccess;
import com.selflearning.chemistree.games.FormulaTransformations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameProvider {

    private List<String> listForRV = new ArrayList<>();
    private List<Boolean> isButtonsEnableList = new ArrayList<>();

    private List<Acids> acidsList;
    private List<Element> elementList;
    private final int COUNT_OF_BUTTONS = 8;

    private String question;

    GameProvider (Context context){
        acidsList = DatabaseAccess.getInstance(context).getAllAcids();
//        elementList = DatabaseAccess.getInstance(context).getAllElements();
    }
    public void loadData(){

        int randomAcid = (int) (Math.random() * acidsList.size());
//        String formula = acidsList.get(randomAcid).getAcidFormula();
//        List<String> stringList = FormulaTransformations.disassembleFormula(formula);
//
//        question = acidsList.get(randomAcid).getAcidRuName();
//        listForRV.addAll(stringList);

        for(int i = 0; i < COUNT_OF_BUTTONS; i++){
            isButtonsEnableList.add(true);
        }

        for(int i = listForRV.size(); i < COUNT_OF_BUTTONS; i++){
            int randomElement = (int) (Math.random() * elementList.size());
            listForRV.add(elementList.get(randomElement).getSymbol());
        }
        Collections.shuffle(listForRV);
    }

    public List<String> getListForRV() {
        return listForRV;
    }

    public List<Boolean> getIsButtonsEnableList() {
        return isButtonsEnableList;
    }

    public String getQuestion() {
        return question;
    }
}
