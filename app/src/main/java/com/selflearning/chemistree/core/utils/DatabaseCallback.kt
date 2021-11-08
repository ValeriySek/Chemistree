package com.selflearning.chemistree.core.utils

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.selflearning.chemistree.domain.chemistry.elements.AppDatabase
import com.selflearning.chemistree.domain.chemistry.inorganic.bases.Bases
import com.selflearning.chemistree.domain.chemistry.inorganic.bases.BasesDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DatabaseCallback @Inject constructor(
        private val context: Context,
        private val appDatabase: AppDatabase?
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        Log.i("TAGG", "DatabaseCallback onCreate")
        GlobalScope.launch {
            Log.i("TAGG", "DatabaseCallback onCreate launch")
            prePopulateDatabase(appDatabase?.basesDao())
        }
    }

    private fun prePopulateDatabase(basesDao: BasesDao?) {
        Log.i("TAGG", "DatabaseCallback prePopulateDatabase")
        val assets = context.assets
        val jsonString = assets.open("bases.json").bufferedReader().use {
            it.readText()
        }

        val typeToken = object : TypeToken<List<Bases>>() {}.type
        val bases = Gson().fromJson<List<Bases>>(jsonString, typeToken)
        if (basesDao != null) {
            Log.i("TAGG", "basesDao != null prePopulateDatabase")
            basesDao?.insertAllBase(bases)
        } else {
            Log.i("TAGG", "basesDao == null prePopulateDatabase")

        }
    }
}