package com.selflearning.chemistree.utilities;

public class SoundUtilities {

    private String mAssetPath;
    private String mName;
    private int mSoundId;

    public SoundUtilities(String assetPath){
        this.mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String fileName = components[components.length - 1];
        mName = fileName.replace(".wav", "");
    }

    public String getmAssetPath() {
        return mAssetPath;
    }

    public String getmName() {
        return mName;
    }

    public int getmSoundId() {
        return mSoundId;
    }

    public void setmSoundId(int mSoundId) {
        this.mSoundId = mSoundId;
    }
}
