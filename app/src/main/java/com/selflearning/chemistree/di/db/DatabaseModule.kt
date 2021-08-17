package com.selflearning.chemistree.di.db

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.selflearning.chemistree.chemistry.elements.AppDatabase
import com.selflearning.chemistree.chemistry.elements.Element
import com.selflearning.chemistree.chemistry.elements.ElementRequest
import com.selflearning.chemistree.chemistry.inorganic.acids.Acid
import com.selflearning.chemistree.chemistry.inorganic.acids.AcidRequest
import com.selflearning.chemistree.chemistry.inorganic.bases.BaseRequest
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases
import com.selflearning.chemistree.chemistry.inorganic.bases.BasesDao
import com.selflearning.chemistree.chemistry.inorganic.oxides.OxideRequest
import com.selflearning.chemistree.chemistry.inorganic.oxides.Oxides
import com.selflearning.chemistree.utilities.AppConstants
import com.selflearning.chemistree.utilities.extentions.getCationName
import com.selflearning.chemistree.utilities.extentions.getStringResource
import com.selflearning.chemistree.utilities.extentions.replaceOnSubstringString
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
    private lateinit var assets: AssetManager

    private suspend fun prePopulateDatabase(database: AppDatabase?) {

        assets = context.assets
        if (database != null) {
            Log.i("TAGG", "basesDao != null prePopulateDatabase")
            addBases(database)
            addAcids(database)
            addOxides(database)
            addElements(database)
        } else {
            Log.i("TAGG", "basesDao == null prePopulateDatabase")
        }
    }

    private fun addBases(database: AppDatabase) {
        val jsonString = assets.open("bases.json").bufferedReader().use {
            it.readText()
        }
        val typeToken = object : TypeToken<List<BaseRequest>>() {}.type
        val bases = Gson().fromJson<List<BaseRequest>>(jsonString, typeToken)
            Log.i("TAGG", "basesDao != null prePopulateDatabase")
        val bs = bases.map {
            val name = context.getStringResource(it.name)
            Bases(
                formula = it.formula,
                beautifulFormula = it.formula.replaceOnSubstringString(),
                name = name,
                nameCation = name.getCationName(),
                cation = it.cation,
                oxidationState = it.oxidationState,
                classification = it.classification,
                difficult = it.difficult
            )
        }
            database.basesDao().insertAllBase(bs)
    }

    private fun addAcids(database: AppDatabase) {
        val jsonString = assets.open("acids.json").bufferedReader().use {
            it.readText()
        }
        val typeToken = object : TypeToken<List<AcidRequest>>() {}.type
        val acids = Gson().fromJson<List<AcidRequest>>(jsonString, typeToken)
        val ac = acids.map {
            Acid(
                formula = it.formula,
                formulaBeauty = it.formula.replaceOnSubstringString(),
                name = context.getStringResource(it.name),
                nameSalt = context.getStringResource(it.nameSalt),
                anion = it.anion,
                charge = it.charge,
                classification = it.classification,
                difficult = it.difficult
            )
        }
        Log.i("TAGG", "basesDao != null prePopulateDatabase")
        database.acidsDao().insertAll(ac)
    }

    private fun addElements(database: AppDatabase) {
        val jsonString = assets.open("elements.json").bufferedReader().use {
            it.readText()
        }
        val typeToken = object : TypeToken<List<ElementRequest>>() {}.type
        val element = Gson().fromJson<List<ElementRequest>>(jsonString, typeToken)
        Log.i("TAGG", "basesDao != null prePopulateDatabase")
        val el = element.map {
            Element(
                atomicNumber = it.atomicNumber,
                symbol = it.symbol,
                title = context.getStringResource(it.title),
                weight = it.weight,
                group = it.group,
                subgroup = it.subgroup,
                period = it.period,
                block = it.block,
                elementCategory = context.getStringResource(it.elementCategory),
                electronConfiguration = it.electronConfiguration,
                electronsPerShell = it.electronsPerShell,
                phase = it.phase
            )
        }
        database.elementDao().insertAll(el)
    }

    private fun addOxides(database: AppDatabase) {
        val jsonString = assets.open("oxides.json").bufferedReader().use {
            it.readText()
        }
        val typeToken = object : TypeToken<List<OxideRequest>>() {}.type
        val oxides = Gson().fromJson<List<OxideRequest>>(jsonString, typeToken)
        Log.i("TAGG", "basesDao != null prePopulateDatabase")
        val ox = oxides.map {
            Oxides(
                formula = it.formula,
                formulaBeauty = it.formula.replaceOnSubstringString(),
                name = context.getStringResource(it.name),
                charge = it.charge,
                classification = context.getStringResource(it.classification),
                difficult = it.difficult
            )
        }
        database.oxidesDao().insertAll(ox)
    }

    @Provides
    @Singleton
    fun provideDatabase(
            context: Context
    ): AppDatabase {
        Log.i("TAGG", "provideDatabase")
        val instance = Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
//                        val request = OneTimeWorkRequest.Builder(DatabaseWorker::class.java).build()
//                        WorkManager.getInstance(context.applicationContext).enqueue(request)

                            GlobalScope.launch {
                                Log.i("TAGG", "DatabaseCallback onCreate launch")
                                prePopulateDatabase(database)
                            }
                    }
                })
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