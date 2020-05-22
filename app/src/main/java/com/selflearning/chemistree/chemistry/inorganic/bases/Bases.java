package com.selflearning.chemistree.chemistry.inorganic.bases;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.selflearning.chemistree.chemistry.inorganic.Inorganic;

@Entity(tableName = "bases")
public class Bases implements Inorganic {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String formula;
    private String name;
    private String nameCation;
    private String cation;
    private int oxidationState;
    private String classification;
    private int difficult;

//    @Ignore
//    public Bases(int id, String baseFormula, String baseRuName, String baseEnName, String baseFormulaCation, int oxidationStateOfCation, int difficult, int classification) {
//        this.id = id;
//        this.baseFormula = baseFormula.replaceAll("([2-9]*)", "<sub><small>$1</small></sub>");
//        this.baseRuName = baseRuName;
//        this.baseEnName = baseEnName;
//        this.baseRuNameCation = baseRuName.replaceAll("[а-яА-Я]\\s", "");
//        this.baseEnNameCation = baseEnName.replaceAll("\\s[a-zA-Z]", "");
//        this.baseFormulaCation = baseFormulaCation;
//        this.oxidationStateOfCation = oxidationStateOfCation;
//        this.difficult = difficult;
//        this.classification = classification;
//    }

    public Bases(int id, String formula, String name, String nameCation, String cation, int oxidationState, String classification, int difficult) {
        this.id = id;
        this.formula = formula;
        this.name = name;
        this.nameCation = nameCation;
        this.cation = cation;
        this.oxidationState = oxidationState;
        this.classification = classification;
        this.difficult = difficult;
    }
    @Ignore
    public Bases(String formula, String name, String nameCation, String cation, int oxidationState, String classification, int difficult) {
        this.formula = formula;
        this.name = name;
        this.nameCation = nameCation;
        this.cation = cation;
        this.oxidationState = oxidationState;
        this.classification = classification;
        this.difficult = difficult;
    }

    public int getId() {
        return id;
    }

    public String getFormula() {
        return formula;
    }

    public String getName() {
        return name;
    }

    public String getNameCation() {
        return nameCation;
    }

    public String getCation() {
        return cation;
    }

    public int getOxidationState() {
        return oxidationState;
    }

    public String getClassification() {
        return classification;
    }

    public int getDifficult() {
        return difficult;
    }
}
