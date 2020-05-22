package com.selflearning.chemistree.chemistry.inorganic;

import android.content.Context;

import com.selflearning.chemistree.chemistry.inorganic.acids.Acids;
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases;
import com.selflearning.chemistree.dBHelper.DatabaseAccess;

import java.util.List;

public class ChemArray {

    private ChemArray chemArray;

    private List<Bases> basesList;
    private  List<Acids> acidsList;

    public ChemArray(Context context){
            basesList = DatabaseAccess.getInstance(context).getAllBases();
            acidsList = DatabaseAccess.getInstance(context).getAllAcids();
    }

    public List<Bases> getBasesList() {
        return basesList;
    }

    public List<Acids> getAcidsList() {
        return acidsList;
    }
}
