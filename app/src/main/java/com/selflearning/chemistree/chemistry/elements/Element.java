package com.selflearning.chemistree.chemistry.elements;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "elements")
public class Element {

    @PrimaryKey
    private int atomicNumber;
    private String symbol;
    private String title;
    private double weight;
    private int group;
    private String subgroup;
    private int period;
    private String block;
    private String elementCategory;
    private String electronConfiguration;
    private String electronsPerShell;
    private String phase;


    public Element(int atomicNumber, String symbol, String title, double weight, int group, String subgroup, int period, String block, String elementCategory, String electronConfiguration, String electronsPerShell, String phase) {
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
        this.title = title;
        this.weight = weight;
        this.group = group;
        this.subgroup = subgroup;
        this.period = period;
        this.block = block;
        this.elementCategory = elementCategory;
        this.electronConfiguration = electronConfiguration;
        this.electronsPerShell = electronsPerShell;
        this.phase = phase;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public String getSymbol() {
        return symbol;
    }


    public String getTitle() {
        return title;
    }


    public double getWeight() {
        return weight;
    }


    public int getGroup() {
        return group;
    }


    public String getSubgroup() {
        return subgroup;
    }


    public int getPeriod() {
        return period;
    }

    public String getBlock() {
        return block;
    }

    public String getElementCategory() {
        return elementCategory;
    }

    public String getElectronConfiguration() {
        return electronConfiguration;
    }

    public String getElectronsPerShell() {
        return electronsPerShell;
    }

    public String getPhase() {
        return phase;
    }
}
