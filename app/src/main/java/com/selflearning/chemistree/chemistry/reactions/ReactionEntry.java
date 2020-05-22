package com.selflearning.chemistree.chemistry.reactions;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ReactionEntry {

    private static final String TAG = ReactionEntry.class.getSimpleName();

    public final String reaction;
    public final String condition;
    private static Context context;
    private static Activity activity;

    public ReactionEntry(String reaction, String condition) {
        this.reaction = reaction;
        this.condition = condition;
    }

    public static List<ReactionEntry> initReactionList(Context mContext){
        context = mContext;
        InputStream inputStream = null;
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            inputStream = context.getAssets().open("json/reactions/inorganic/hydrogen.json");
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1){
                writer.write(buffer, 0, pointer);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error writing/reading from the JSON file.", e);
        }finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                Log.e(TAG, "Error closing the input stream.", exception);
            }
        }
        String jsonReactionString = writer.toString();
        Gson gson = new Gson();
        Type reactionListType = new TypeToken<ArrayList<ReactionEntry>>() {

        }.getType();
        return gson.fromJson(jsonReactionString, reactionListType);
    }
}
