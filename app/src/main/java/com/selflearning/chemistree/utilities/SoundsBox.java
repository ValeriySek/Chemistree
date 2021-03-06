package com.selflearning.chemistree.utilities;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoundsBox {

    private static final String SOUNDS_FOLDER = "all_sounds";
    private static final int MAX_SOUNDS = 5;
    private AssetManager mAssets;
    private List<SoundUtilities> mSoundsList = new ArrayList<>();
    private SoundPool mSoundPool;

    public SoundsBox(Context context){
        mAssets = context.getAssets();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(MAX_SOUNDS)
                    .setAudioAttributes(audioAttributes)
                    .build();

        }else {
            mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        }
        loadSounds();
    }

    private void loadSounds(){
        String[] soundName;
        try{
            soundName = mAssets.list(SOUNDS_FOLDER);
        } catch (IOException e) {
            return;
        }
        for(String filename : soundName){
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                SoundUtilities sound = new SoundUtilities(assetPath);
                load(sound);
                mSoundsList.add(sound);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void load(SoundUtilities sound) throws IOException{
        AssetFileDescriptor assetFileDescriptor = mAssets.openFd(sound.getmAssetPath());
        int soundId = mSoundPool.load(assetFileDescriptor, 1);
        sound.setmSoundId(soundId);
    }

    public void play(SoundUtilities sound){
        Integer soundId = sound.getmSoundId();
        if(soundId == null){
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release(){
        mSoundPool.release();
    }

    public List<SoundUtilities> getmSoundsList() {
        return mSoundsList;
    }
}














