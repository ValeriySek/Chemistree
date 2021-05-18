package com.selflearning.chemistree.chemistry.elements;

import android.app.Application;
import android.os.AsyncTask;

import com.selflearning.chemistree.chemistry.inorganic.acids.Acids;
import com.selflearning.chemistree.chemistry.inorganic.acids.AcidsDao;
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases;
import com.selflearning.chemistree.chemistry.inorganic.bases.BasesDao;
import selflearning.chemistree.domain.chemistry.elements.Element;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ElementRepository {
//3    private static ElementDao elementDao;
    private BasesDao basesDao;
    private static AcidsDao acidsDao;
    private List<Element> mAllElements;
    private List<Bases> basesList;
    private List<Acids> acidsList;

    public ElementRepository(Application application){
//        AppDatabase database = AppDatabase.getInstance(application);
//        elementDao = database.elementDao();
//        basesDao = database.basesDao();
//        acidsDao = database.acidsDao();
        acidsList = acidsDao.getAcids();
//        basesList = basesDao.getAllBases();
//        mAllElements = elementDao.getAllElements();
    }

//    public List<Element> getAllElements() {
//        try {
//            return new GetElements().execute().get();
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public List<Bases> getBasesList() {
        return basesList;
    }

    public List<Acids> getAcidsList() {
        try {
            return new GetAcids().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public Element getElement(int id) {
//        try {
//            return new GetElementTask().execute(id).get();
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public List<Acids> getAcidsByLvl(int lvl){
        try {
            return new GetAcidsByLvl().execute(lvl).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private static class GetElementTask extends AsyncTask<Integer, Void, Element>{
//        @Override
//        protected Element doInBackground(Integer... integers) {
//            return elementDao.getElementById(integers[0]);
//        }
//    }

    private static class GetAcids extends AsyncTask<Void, Void, List<Acids>>{
        @Override
        protected List<Acids> doInBackground(Void... voids) {
            return acidsDao.getAcids();
        }
    }

//    private static class GetElements extends AsyncTask<Void, Void, List<Element>>{
//        @Override
//        protected List<Element> doInBackground(Void... voids) {
//            return elementDao.getAllElements();
//        }
//    }

    private static class GetAcidsByLvl extends AsyncTask<Integer, Void, List<Acids>>{
        @Override
        protected List<Acids> doInBackground(Integer... integers) {
            return acidsDao.getAcidsByLevel(integers[0]);
        }
    }
}
