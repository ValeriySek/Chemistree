package com.selflearning.chemistree.dBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import com.selflearning.chemistree.chemistry.inorganic.acids.Acids;
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    public final String TAG = this.getClass().getSimpleName();

    private static Cursor cursor;
    private static SQLiteOpenHelper openHelper;
    private static SQLiteDatabase db;
    private static DatabaseAccess instance;
    private static List<Bases> basesList = new ArrayList<>();
    private static List<Acids> acidsList = new ArrayList<>();
//    private static BaseTask baseTask;


    private DatabaseAccess(Context context){
        openHelper = new DBHelper(context);
        Log.d(TAG, "initeDB " + basesList.size());
        loadData();
        Log.d(TAG, "afterIniteDb " + basesList.size());
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    private void loadData(){
        loadBases();
        loadAcids();
    }


    public List<Tables> getAllTables(){
        db = openHelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM Maintables", null);
        List<Tables> tablesList = new ArrayList<>();
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Tables tables = new Tables(cursor.getString(cursor.getColumnIndex("Name")),
                        cursor.getString(cursor.getColumnIndex("Image")),
                        cursor.getInt(cursor.getColumnIndex("ID")));
                tablesList.add(tables);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return tablesList;
    }

//
//    public List<Element> getAllElements(){
//        db = openHelper.getWritableDatabase();
//        cursor = db.rawQuery("SELECT Id, Symbol, RuTitle, Weight FROM Periodictable", null);
//        List<Element> elementList = new ArrayList<>();
//        if (cursor.moveToFirst()){
//            while (!cursor.isAfterLast()){
//                Element element = new Element(cursor.getInt(cursor.getColumnIndex("Id")),
//                        cursor.getString(cursor.getColumnIndex("Symbol")),
//                        cursor.getString(cursor.getColumnIndex("RuTitle")),
//                        cursor.getDouble(cursor.getColumnIndex("Weight")));
//                elementList.add(element);
//                cursor.moveToNext();
//            }
//        }
//        if(cursor != null){
//            cursor.close();
//        }
//
//        db.close();
//        return elementList;
//    }
//
//    public Element getElementFromTable(String id){
//        db = openHelper.getWritableDatabase();
//        cursor = db.rawQuery("SELECT Id, Symbol, RuTitle, Weight FROM Periodictable WHERE Id = '"+id+"'", null);
//        Element element = null;
//        while (cursor.moveToNext()){
//            element = new Element(cursor.getInt(cursor.getColumnIndex("Id")),
//                    cursor.getString(cursor.getColumnIndex("Symbol")),
//                    cursor.getString(cursor.getColumnIndex("RuTitle")),
//                    cursor.getDouble(cursor.getColumnIndex("Weight")));
//        }
//        if(cursor != null){
//            cursor.close();
//        }
//        db.close();
//        return element;
//    }

    private void loadAcids(){
        Log.d(TAG, "inite loadBases");
//        new AcidTask().execute();
    }

    public List<Acids> getAllAcids(){
        return acidsList;
    }

    private void loadBases(){
        Log.d(TAG, "inite loadBases");
//        new BaseTask().execute();
    }

    public static List<Bases> getAllBases(){
        return basesList;
    }

//    private static class BaseTask extends AsyncTask<Void, Void, Void>{
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            if(voids != null){
//                db = openHelper.getWritableDatabase();
//                cursor = db.rawQuery("SELECT Id, FormulaBase, NameRuBase, NameEnBase, OxidationState, Cation FROM Bases", null);
//                if(cursor.moveToFirst()){
//                    while (!cursor.isAfterLast()){
//                        Bases base = new Bases(cursor.getInt(cursor.getColumnIndex("Id")),
//                                cursor.getString(cursor.getColumnIndex("FormulaBase")),
//                                cursor.getString(cursor.getColumnIndex("NameRuBase")),
//                                cursor.getString(cursor.getColumnIndex("NameEnBase")),
//                                cursor.getString(cursor.getColumnIndex("Cation")),
//                                cursor.getInt(cursor.getColumnIndex("OxidationState"))
////                        cursor.getInt(cursor.getColumnIndex("Difficult")),
////                        cursor.getInt(cursor.getColumnIndex("Classification"))
//                        );
//                        basesList.add(base);
//                        cursor.moveToNext();
//                    }
//                }
//                if(cursor != null){
//                    cursor.close();
//                }
//                db.close();
//            }
//            Log.d("Load", "Bases loaded: " + System.currentTimeMillis());
//            return null;
//        }
//    }


//    private static class AcidTask extendends AsyncTask<Void, Void, Void>{
//        @Override
//        protected Void doInBackground(Void... voids) {
//            if(voids != null){
//                db = openHelper.getWritableDatabase();
//                cursor = db.rawQuery("SELECT Id, FormulaAcid, NameRuAcid, SaltRuName, Anion, ChargeOfAnion, Difficult FROM Acids", null);
//                if(cursor.moveToFirst()){
//                    while (!cursor.isAfterLast()){
//                        Acids acid = new Acids(cursor.getInt(cursor.getColumnIndex("Id")),
//                                cursor.getString(cursor.getColumnIndex("FormulaAcid")),
//                                cursor.getString(cursor.getColumnIndex("NameRuAcid")),
//                                cursor.getString(cursor.getColumnIndex("SaltRuName")),
//                                cursor.getString(cursor.getColumnIndex("Anion")),
//                                cursor.getInt(cursor.getColumnIndex("ChargeOfAnion")),
//                                cursor.getInt(cursor.getColumnIndex("Difficult")));
//                        acidsList.add(acid);
//                        cursor.moveToNext();
//                    }
//                }
//                if(cursor != null){
//                    cursor.close();
//                }
//                db.close();
//            }
//            Log.d("Load", "Acids loaded: " + System.currentTimeMillis());
//            return null;
//        }
//    }


}
