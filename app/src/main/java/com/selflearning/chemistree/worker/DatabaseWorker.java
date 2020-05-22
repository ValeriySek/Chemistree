package com.selflearning.chemistree.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.selflearning.chemistree.chemistry.elements.AppDatabase;
import com.selflearning.chemistree.chemistry.elements.Element;
import com.selflearning.chemistree.chemistry.inorganic.acids.Acids;
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases;
import com.selflearning.chemistree.chemistry.inorganic.oxides.Oxides;
import com.selflearning.chemistree.constants.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static androidx.work.ListenableWorker.Result.success;

public class DatabaseWorker extends Worker {

    public DatabaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());

            InputStream inputStream = getApplicationContext().getAssets().open("bases.json");
            JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));


            Log.i("INFORMA", "before " + System.currentTimeMillis());

            List<Element> elements = loadElements();
            List<Bases> bases = loadBases();
            List<Acids> acids = loadAcids();
            List<Oxides> oxides = loadOxides();

            appDatabase.elementDao().insertAll(elements);
            appDatabase.basesDao().insertAllBase(bases);
            appDatabase.acidsDao().insertAll(acids);
            appDatabase.oxidesDao().insertAll(oxides);

            Log.i("INFORMA", "after " + System.currentTimeMillis());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return success();
    }

    private List<Element> loadElements(){
        List<Element> elements = new ArrayList<>();

        InputStream inputStream;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            inputStream = getApplicationContext().getAssets().open(AppConstants.ELEMENT_DATA_FILENAME);
            br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            JSONArray array = new JSONArray(sb.toString());
            Log.i("INFORMA", "" + array.length());
            Element element;
            for(int i = 0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                int atomicNumber = object.getInt("atomicNumber");
                String symbol = object.getString("symbol");

                int titleId = getApplicationContext().getResources().getIdentifier(object.getString("title"), "string", getApplicationContext().getPackageName());
                String title = getApplicationContext().getResources().getString(titleId);

                double weight = object.getDouble("weight");
                int group = object.getInt("group");
                String subgroup = object.getString("subgroup");
                int period = object.getInt("period");
                String block = object.getString("block");
                String elementCategory = getApplicationContext().getResources().getString(
                        getApplicationContext().getResources().getIdentifier(
                                object.getString("elementCategory"), "string", getApplicationContext().getPackageName())
                );

                String electronConfiguration = object.getString("electronConfiguration");
                String electronsPerShell = object.getString("electronsPerShell");
                String phase = object.getString("phase");

                element = new Element(atomicNumber, symbol, title, weight,group, subgroup, period, block, elementCategory, electronConfiguration, electronsPerShell, phase);
                elements.add(element);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return elements;
    }

    private List<Bases> loadBases(){
        List<Bases> bases = new ArrayList<>();

        InputStream inputStream;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            inputStream = getApplicationContext().getAssets().open("bases.json");
            br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            JSONArray array = new JSONArray(sb.toString());
            Log.i("INFORMA", "" + array.length());
            Bases base;
            for(int i = 0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);

                String formula = object.getString("formula");
                String formulaBeauty = formula.replaceAll("([2-9]*)", "<sub><small>$1</small></sub>");
                String name = getApplicationContext().getResources().getString(
                        getApplicationContext().getResources().getIdentifier(
                                object.getString("name"), "string", getApplicationContext().getPackageName()
                        ));

                String nameCation;
                if(Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage())){
                    nameCation = name.replaceAll("\\s[a-zA-Z]*", "");
                } else{
                    nameCation = name.replaceAll("[а-яА-Я]*\\s", "");
                }
                String cation = object.getString("cation");
                int oxidationState = object.getInt("oxidationState");
                String classification = object.getString("classification");
                int difficult = object.getInt("difficult");

                base = new Bases(formula, name, nameCation, cation, oxidationState, classification, difficult);
                bases.add(base);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bases;
    }

    private List<Acids> loadAcids(){
        List<Acids> acids = new ArrayList<>();

        InputStream inputStream;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            inputStream = getApplicationContext().getAssets().open("acids.json");
            br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            JSONArray array = new JSONArray(sb.toString());
            Log.i("INFORMA", "" + array.length());
            Acids acid;
            for(int i = 0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);

                String formula = object.getString("formula");
                String formulaBeauty = formula.replaceAll("([2-9]*)", "<sub><small>$1</small></sub>");
                String name = getApplicationContext().getResources().getString(
                        getApplicationContext().getResources().getIdentifier(
                                object.getString("name"), "string", getApplicationContext().getPackageName()
                        ));

                String nameSalt = getApplicationContext().getResources().getString(
                        getApplicationContext().getResources().getIdentifier(
                                object.getString("nameSalt"), "string", getApplicationContext().getPackageName()
                        ));
                String anion = object.getString("anion");
                int charge = object.getInt("charge");
                String classification = object.getString("classification");
                int difficult = object.getInt("difficult");


                acid = new Acids(formula, formulaBeauty, name, nameSalt, anion, charge, classification, difficult);
                acids.add(acid);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return acids;
    }

    private List<Oxides> loadOxides(){
        List<Oxides> oxides = new ArrayList<>();

        InputStream inputStream;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            inputStream = getApplicationContext().getAssets().open("oxides.json");
            br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            JSONArray array = new JSONArray(sb.toString());
            Log.i("INFORMA", "" + array.length());
            Oxides oxide;
            for(int i = 0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);

                String formula = object.getString("formula");
                String formulaBeauty = formula.replaceAll("([2-9]*)", "<sub><small>$1</small></sub>");
                String name = getApplicationContext().getResources().getString(
                        getApplicationContext().getResources().getIdentifier(
                                object.getString("name"), "string", getApplicationContext().getPackageName()
                        ));

                int charge = object.getInt("charge");

                String classification = getApplicationContext().getResources().getString(
                        getApplicationContext().getResources().getIdentifier(
                                object.getString("classification"), "string", getApplicationContext().getPackageName()
                        ));
                int difficult = object.getInt("difficult");


                oxide = new Oxides(formula, formulaBeauty, name, charge, classification, difficult);
                oxides.add(oxide);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return oxides;
    }
}
