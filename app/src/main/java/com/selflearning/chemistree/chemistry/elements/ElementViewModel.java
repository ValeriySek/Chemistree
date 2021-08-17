package com.selflearning.chemistree.chemistry.elements;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.selflearning.chemistree.chemistry.inorganic.bases.Bases;
import selflearning.chemistree.domain.chemistry.elements.Element;

import java.util.List;


public class ElementViewModel extends AndroidViewModel {

    private Repository repository;
    private List<Element> mAllElements;
    private List<Bases> bases;

    public ElementViewModel(@NonNull Application application) {
        super(application);
//        repository = new ElementRepository(application);
//        mAllElements = repository.getAllElements();
//        bases = repository.getBasesList();
    }

    public List<Bases> getBases() {
        return bases;
    }

    public List<Element> getAllElements() {
        return mAllElements;
    }
}
