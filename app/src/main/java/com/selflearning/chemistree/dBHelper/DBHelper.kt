package com.selflearning.chemistree.dBHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DBHelper(context: Context?) : SQLiteAssetHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        super.onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        private const val DB_NAME = "ListOfTables.db"
        private const val DB_VERSION = 4
    }
}