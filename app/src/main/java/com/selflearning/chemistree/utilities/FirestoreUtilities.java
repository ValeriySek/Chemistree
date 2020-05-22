package com.selflearning.chemistree.utilities;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.selflearning.chemistree.data.User;
import com.selflearning.chemistree.data.WinData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirestoreUtilities {

    private FirebaseFirestore db;
    private User user;
    private DocumentReference reference;

    private MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Long>> mutableLiveData1 = new MutableLiveData<>();

    public FirestoreUtilities() {
        db = FirebaseFirestore.getInstance();
        user = User.getInstance();
        reference = db.collection("users").document(user.getUid());
    }

    public void saveScore(String gameName, long score){
        CollectionReference refer = reference.collection("games").document(gameName)
                .collection("scores");
//        Map<String, Object> gameScore = new HashMap<>();
//        gameScore.put("field", score);
        refer.add(new WinData(System.currentTimeMillis(), score));
    }

    public LiveData<String> getScore(String gameName){
        DocumentReference refer = reference.collection("games").document(gameName);
        refer.collection("games").document(gameName);
        refer.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Log.i("FirestoreUtilities", "Sucsess " + documentSnapshot.getLong("field"));
                    mutableLiveData.setValue(String.valueOf(documentSnapshot.getLong("field")));
                } else {
                    Log.i("FirestoreUtilities", "Document not exist " + documentSnapshot.getLong("field"));
                    mutableLiveData.setValue("0");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("FirestoreUtilities", "Error " + e);
            }
        });
        return mutableLiveData;
    }

    public LiveData<List<Long>> getScores(String gameName){
        List<Long> winDataScore = new ArrayList<>();
        CollectionReference refer = reference.collection("games").document(gameName)
                .collection("scores");
        refer.orderBy("timeInMillis").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        WinData win = document.toObject(WinData.class);
                        winDataScore.add(win.getScore());
                    }
                } else {
                    Log.i("FirestoreUtilities", "Cant get documents " + task.getException());
                }
                mutableLiveData1.setValue(winDataScore);
            }
        });

        return mutableLiveData1;
    }
}
