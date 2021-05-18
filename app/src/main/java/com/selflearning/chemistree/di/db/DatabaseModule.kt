package com.selflearning.chemistree.di.db

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.selflearning.chemistree.chemistry.elements.AppDatabase
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases
import com.selflearning.chemistree.chemistry.inorganic.bases.BasesDao
import com.selflearning.chemistree.constants.AppConstants
import com.selflearning.chemistree.core.utils.DatabaseCallback
import com.selflearning.chemistree.worker.DatabaseWorker
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
class DatabaseModule(
        val context: Context
) {

    private var database: AppDatabase? = null

    private suspend fun prePopulateDatabase(basesDao: BasesDao?) {
        Log.i("TAGG", "DatabaseCallback prePopulateDatabase")
        val assets = context.assets
        val jsonString = assets.open("bases.json").bufferedReader().use {
            it.readText()
        }

        val typeToken = object : TypeToken<List<Bases>>() {}.type
        val bases = Gson().fromJson<List<Bases>>(jsonString, typeToken)
        if (basesDao != null) {
            Log.i("TAGG", "basesDao != null prePopulateDatabase")
            basesDao.insertAllBase(bases)
        } else {
            Log.i("TAGG", "basesDao == null prePopulateDatabase")

        }
    }

    @Provides
    @Singleton
    fun provideDatabase(
            context: Context,
            assets: AssetManager
    ): AppDatabase {
        Log.i("TAGG", "provideDatabase")
        val instance = Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                            GlobalScope.launch {
                                Log.i("TAGG", "DatabaseCallback onCreate launch")
                                prePopulateDatabase(database?.basesDao())
                            }
                    }
                })
//                .addCallback(DatabaseCallback(context, database))
                .build()
        database = instance
        return instance
    }

    @Provides
    @Singleton
    fun provideBasesDao(database: AppDatabase): BasesDao {
        return database.basesDao()
    }
}