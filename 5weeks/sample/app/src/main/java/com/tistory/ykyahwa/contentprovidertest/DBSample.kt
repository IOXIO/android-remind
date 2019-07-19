package com.tistory.ykyahwa.contentprovidertest

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns


class DBSample(context: Context): SQLiteOpenHelper(context, "UserData.db", null, 1) {

    private val COLUMNS = arrayOf("contents TEXT", "name TEXT", "num INTEGER")
    var TABLE_NAME = "my_table"

    override fun onCreate(db: SQLiteDatabase) {
        var sql = "create table " + TABLE_NAME +
                " (" + BaseColumns._ID + " integer primary key autoincrement "
        for (i in COLUMNS.indices) {
            sql += ", " + COLUMNS[i]
        }
        sql += " ) "
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun getItem(): List<ItemRow> {
        val list : ArrayList<ItemRow> = arrayListOf()
        try {
            beginTransaction()
            val c = getAll(TABLE_NAME)
            if (c != null) {
                val total = c.getCount()
                if (total > 0) {
                    c.moveToFirst()
                    while (!c.isAfterLast()) {
                        val contents = c.getString(1)
                        val name = c.getString(2)
                        val num = c.getInt(3)
                        list.add(ItemRow(contents, name, num))
                        c.moveToNext()
                    }
                }
                c.close()
            }
        } catch (e: SQLiteException) {
            e.printStackTrace()
        } finally {
            endTransaction()
        }
        return list
    }

    @Throws(SQLiteException::class)
    fun setItem(contents: String, name: String, num: Int) {
        val values = ContentValues()
        values.put("contents", contents)
        values.put("name", name)
        values.put("num", num)
        insert(TABLE_NAME, values)
    }

    fun setDelete() {
        AllDelete(TABLE_NAME)
    }

    @Throws(SQLiteException::class)
    protected fun getAll(tableName: String): Cursor? {
        return readableDatabase.query(tableName, /*"date desc"*/null, null, null, null, null, null)
    }

    protected fun beginTransaction() {
        writableDatabase.beginTransaction()
    }

    protected fun endTransaction() {
        writableDatabase.setTransactionSuccessful()   // db 속도 향상
        writableDatabase.endTransaction()
    }

    @Throws(SQLiteException::class)
    protected fun insert(tableName: String, values: ContentValues) {
        writableDatabase.insert(tableName, null, values)
    }

    @Throws(SQLiteException::class)
    protected fun AllDelete(tableName: String) {
        writableDatabase.delete(tableName, null, null)
    }

}