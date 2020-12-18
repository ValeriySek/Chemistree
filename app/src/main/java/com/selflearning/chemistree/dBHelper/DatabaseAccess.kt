package com.selflearning.chemistree.dBHelper

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases
import java.util.*

class DatabaseAccess private constructor(context: Context) {
    private lateinit var cursor: Cursor
    private lateinit var openHelper: SQLiteOpenHelper
    private lateinit var db: SQLiteDatabase
    val allBases: List<Bases> = ArrayList()
    val TAG = this.javaClass.simpleName
    private fun loadData() {
        loadBases()
        loadAcids()
    }

    fun allTables(): List<Tables> {
        db = openHelper.writableDatabase
        cursor = db.rawQuery("SELECT * FROM Maintables", null)
        val tablesList: MutableList<Tables> = ArrayList()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val tables = Tables(cursor.getString(cursor.getColumnIndex("Name")),
                        cursor.getString(cursor.getColumnIndex("Image")),
                        cursor.getInt(cursor.getColumnIndex("ID")))
                tablesList.add(tables)
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return tablesList
    }

    private fun loadAcids() {
        Log.d(TAG, "inite loadBases")
    }

    private fun loadBases() {
        Log.d(TAG, "inite loadBases")
    }

    companion object {

        private var instance: DatabaseAccess? = null

        @JvmStatic
        fun getInstance(context: Context): DatabaseAccess? {
            if (instance == null) {
                instance = DatabaseAccess(context)
            }
            return instance
        }
    }

    init {
        openHelper = DBHelper(context)
        Log.d(TAG, "initeDB " + allBases.size)
        loadData()
        Log.d(TAG, "afterIniteDb " + allBases.size)
    }
}