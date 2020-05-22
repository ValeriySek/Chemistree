package com.selflearning.chemistree.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ReportModels implements Parcelable {

    private String chosenName;
    private String correctName;
    private String chosenDesc;
    private String correctDesc;

    public ReportModels(String chosenName, String correctName, String chosenDesc, String correctDesc) {
        this.chosenName = chosenName;
        this.correctName = correctName;
        this.chosenDesc = chosenDesc;
        this.correctDesc = correctDesc;
    }

    public String getChosenName() {
        return chosenName;
    }

    public String getCorrectName() {
        return correctName;
    }

    public String getChosenDesc() {
        return chosenDesc;
    }

    public String getCorrectDesc() {
        return correctDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(chosenName);
        dest.writeString(correctName);
        dest.writeString(chosenDesc);
        dest.writeString(correctDesc);
    }

    protected ReportModels(Parcel in){
        chosenName = in.readString();
        correctName = in.readString();
        chosenDesc = in.readString();
        correctDesc = in.readString();
    }

    public static Creator<ReportModels> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<ReportModels> CREATOR = new Creator<ReportModels>() {
        @Override
        public ReportModels createFromParcel(Parcel in) {
            return new ReportModels(in);
        }

        @Override
        public ReportModels[] newArray(int size) {
            return new ReportModels[size];
        }
    };
}
