package com.selflearning.chemistree.worker

import android.content.Context
import android.util.Log
import androidx.work.WorkerParameters
import androidx.work.ListenableWorker
import androidx.work.Worker
import com.google.gson.stream.JsonReader
import com.selflearning.chemistree.chemistry.elements.AppDatabase
import com.selflearning.chemistree.chemistry.inorganic.acids.Acids
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases
import com.selflearning.chemistree.chemistry.inorganic.oxides.Oxides
import com.selflearning.chemistree.constants.AppConstants
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONException
import selflearning.chemistree.domain.chemistry.elements.Element
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.nio.charset.StandardCharsets
import java.util.*
import javax.inject.Inject

class DatabaseWorker @Inject constructor(
        val database: AppDatabase,
        context: Context,
        workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        try {
//            val appDatabase: AppDatabase = AppDatabase.getInstance(applicationContext)
            val inputStream = applicationContext.assets.open("bases.json")
            val jsonReader = JsonReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
            Log.i("INFORMA", "before " + System.currentTimeMillis())
            val elements = loadElements()
            val bases = loadBases()
            val acids = loadAcids()
            val oxides = loadOxides()

//            appDatabase.elementDao().insertAll(elements);
            database.basesDao().insertAllBase(bases)
            database.acidsDao().insertAll(acids)
            database.oxidesDao().insertAll(oxides)
            Log.i("INFORMA", "after " + System.currentTimeMillis())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Result.success()
    }

    private fun loadElements(): List<Element> {
        val elements: MutableList<Element> = ArrayList()
        val inputStream: InputStream
        val sb = StringBuilder()
        var br: BufferedReader? = null
        try {
            inputStream = applicationContext.assets.open(AppConstants.ELEMENT_DATA_FILENAME)
            br = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
            var temp: String?
            while (br.readLine().also { temp = it } != null) sb.append(temp)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                br!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        try {
            val array = JSONArray(sb.toString())
            Log.i("INFORMA", "" + array.length())
            var element: Element
            for (i in 0 until array.length()) {
                val `object` = array.getJSONObject(i)
                val atomicNumber = `object`.getInt("atomicNumber")
                val symbol = `object`.getString("symbol")
                val titleId = applicationContext.resources.getIdentifier(`object`.getString("title"), "string", applicationContext.packageName)
                val title = applicationContext.resources.getString(titleId)
                val weight = `object`.getDouble("weight")
                val group = `object`.getInt("group")
                val subgroup = `object`.getString("subgroup")
                val period = `object`.getInt("period")
                val block = `object`.getString("block")
                val elementCategory = applicationContext.resources.getString(
                        applicationContext.resources.getIdentifier(
                                `object`.getString("elementCategory"), "string", applicationContext.packageName)
                )
                val electronConfiguration = `object`.getString("electronConfiguration")
                val electronsPerShell = `object`.getString("electronsPerShell")
                val phase = `object`.getString("phase")
                element = Element(atomicNumber, symbol, title, weight, group, subgroup, period, block, elementCategory, electronConfiguration, electronsPerShell, phase)
                elements.add(element)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return elements
    }

    private fun loadBases(): List<Bases> {
        val bases: MutableList<Bases> = ArrayList()
        val inputStream: InputStream
        val sb = StringBuilder()
        var br: BufferedReader? = null
        try {
            inputStream = applicationContext.assets.open("bases.json")
            br = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
            var temp: String?
            while (br.readLine().also { temp = it } != null) sb.append(temp)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                br!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        try {
            val array = JSONArray(sb.toString())
            Log.i("INFORMA", "" + array.length())
            var base: Bases
            for (i in 0 until array.length()) {
                val `object` = array.getJSONObject(i)
                val formula = `object`.getString("formula")
                val formulaBeauty = formula.replace("([2-9]*)".toRegex(), "<sub><small>$1</small></sub>")
                val name = applicationContext.resources.getString(
                        applicationContext.resources.getIdentifier(
                                `object`.getString("name"), "string", applicationContext.packageName
                        ))
                var nameCation: String
                nameCation = if (Locale.getDefault().language == Locale("en").language) {
                    name.replace("\\s[a-zA-Z]*".toRegex(), "")
                } else {
                    name.replace("[а-яА-Я]*\\s".toRegex(), "")
                }
                val cation = `object`.getString("cation")
                val oxidationState = `object`.getInt("oxidationState")
                val classification = `object`.getString("classification")
                val difficult = `object`.getInt("difficult")
                base = Bases(formula, name, nameCation, cation, oxidationState, classification, difficult)
                bases.add(base)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return bases
    }

    private fun loadAcids(): List<Acids> {
        val acids: MutableList<Acids> = ArrayList()
        val inputStream: InputStream
        val sb = StringBuilder()
        var br: BufferedReader? = null
        try {
            inputStream = applicationContext.assets.open("acids.json")
            br = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
            var temp: String?
            while (br.readLine().also { temp = it } != null) sb.append(temp)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                br!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        try {
            val array = JSONArray(sb.toString())
            Log.i("INFORMA", "" + array.length())
            var acid: Acids
            for (i in 0 until array.length()) {
                val `object` = array.getJSONObject(i)
                val formula = `object`.getString("formula")
                val formulaBeauty = formula.replace("([2-9]*)".toRegex(), "<sub><small>$1</small></sub>")
                val name = applicationContext.resources.getString(
                        applicationContext.resources.getIdentifier(
                                `object`.getString("name"), "string", applicationContext.packageName
                        ))
                val nameSalt = applicationContext.resources.getString(
                        applicationContext.resources.getIdentifier(
                                `object`.getString("nameSalt"), "string", applicationContext.packageName
                        ))
                val anion = `object`.getString("anion")
                val charge = `object`.getInt("charge")
                val classification = `object`.getString("classification")
                val difficult = `object`.getInt("difficult")
                acid = Acids(formula, formulaBeauty, name, nameSalt, anion, charge, classification, difficult)
                acids.add(acid)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return acids
    }

    private fun loadOxides(): List<Oxides> {
        val oxides: MutableList<Oxides> = ArrayList()
        val inputStream: InputStream
        val sb = StringBuilder()
        var br: BufferedReader? = null
        try {
            inputStream = applicationContext.assets.open("oxides.json")
            br = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
            var temp: String?
            while (br.readLine().also { temp = it } != null) sb.append(temp)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                br!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        try {
            val array = JSONArray(sb.toString())
            Log.i("INFORMA", "" + array.length())
            var oxide: Oxides
            for (i in 0 until array.length()) {
                val `object` = array.getJSONObject(i)
                val formula = `object`.getString("formula")
                val formulaBeauty = formula.replace("([2-9]*)".toRegex(), "<sub><small>$1</small></sub>")
                val name = applicationContext.resources.getString(
                        applicationContext.resources.getIdentifier(
                                `object`.getString("name"), "string", applicationContext.packageName
                        ))
                val charge = `object`.getInt("charge")
                val classification = applicationContext.resources.getString(
                        applicationContext.resources.getIdentifier(
                                `object`.getString("classification"), "string", applicationContext.packageName
                        ))
                val difficult = `object`.getInt("difficult")
                oxide = Oxides(formula, formulaBeauty, name, charge, classification, difficult)
                oxides.add(oxide)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return oxides
    }
}