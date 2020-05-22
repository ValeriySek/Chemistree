package com.selflearning.chemistree.chemistry.inorganic.oxides;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.selflearning.chemistree.chemistry.inorganic.Inorganic;

@Entity(tableName = "oxides")
public class Oxides implements Inorganic {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String formula;
    private String formulaBeauty;
    private String name;
    private int charge;
    private String classification;
    private int difficult;

    public Oxides(int id, String formula, String formulaBeauty, String name, int charge, String classification, int difficult) {
        this.id = id;
        this.formula = formula;
        this.formulaBeauty = formulaBeauty;
        this.name = name;
        this.charge = charge;
        this.classification = classification;
        this.difficult = difficult;
    }

    @Ignore
    public Oxides(String formula, String formulaBeauty, String name, int charge, String classification, int difficult) {
        this.formula = formula;
        this.formulaBeauty = formulaBeauty;
        this.name = name;
        this.charge = charge;
        this.classification = classification;
        this.difficult = difficult;
    }

    public int getId() {
        return id;
    }

    public String getFormula() {
        return formula;
    }

    public String getFormulaBeauty() {
        return formulaBeauty;
    }

    public String getName() {
        return name;
    }

    public int getCharge() {
        return charge;
    }

    public String getClassification() {
        return classification;
    }

    public int getDifficult() {
        return difficult;
    }
}
