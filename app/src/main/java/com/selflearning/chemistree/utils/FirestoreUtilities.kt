package com.selflearning.chemistree.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.selflearning.chemistree.data.User
import com.selflearning.chemistree.data.WinData
import java.util.*

class FirestoreUtilities {

    private val db: FirebaseFirestore
    private val user: User?
    private lateinit var reference: DocumentReference
    private val mutableLiveData = MutableLiveData<String>()
    private val mutableLiveData1 = MutableLiveData<List<Long>>()

    fun saveScore(gameName: String?, score: Long) {
        val refer = reference?.collection("games")?.document(gameName!!)
                .collection("scores")
        //        Map<String, Object> gameScore = new HashMap<>();
//        gameScore.put("field", score);
        refer.add(WinData(System.currentTimeMillis(), score))
    }

    fun getScore(gameName: String?): LiveData<String> {
        val refer = reference.collection("games").document(gameName!!)
        refer.collection("games").document(gameName)
        refer.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                Log.i("FirestoreUtilities", "Sucsess " + documentSnapshot.getLong("field"))
                mutableLiveData.setValue(documentSnapshot.getLong("field").toString())
            } else {
                Log.i("FirestoreUtilities", "Document not exist " + documentSnapshot.getLong("field"))
                mutableLiveData.setValue("0")
            }
        }.addOnFailureListener { e -> Log.i("FirestoreUtilities", "Error $e") }
        return mutableLiveData
    }

    fun getScores(gameName: String?): LiveData<List<Long>> {
        val winDataScore: MutableList<Long> = ArrayList()
        val refer = reference.collection("games").document(gameName!!)
                .collection("scores")
        refer.orderBy("timeInMillis").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    val (_, score) = document.toObject(WinData::class.java)
                    winDataScore.add(score)
                }
            } else {
                Log.i("FirestoreUtilities", "Cant get documents " + task.exception)
            }
            mutableLiveData1.value = winDataScore
        }
        return mutableLiveData1
    }

    init {
        db = FirebaseFirestore.getInstance()
        user = User.instance
        user?.let { reference = db.collection("users").document(user.uid) }
    }
}