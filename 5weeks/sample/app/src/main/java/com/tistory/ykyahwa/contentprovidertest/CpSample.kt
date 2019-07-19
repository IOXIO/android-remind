package com.tistory.ykyahwa.contentprovidertest

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.support.annotation.Nullable
import android.util.Log


class CpSample : ContentProvider() {

    private var mDatabase: SQLiteDatabase? = null
    private val STUDENTS_PROJECTION_MAP: HashMap<String, String>? = null

    companion object {
        val PROVIDER_NAME = "com.ykyahwa.cptest"
        val GET_ALL = 1
        val INSERT = 2
        val UPDATE = 3
        val DELETE = 4
        val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(PROVIDER_NAME, "getAll", GET_ALL)
            addURI(PROVIDER_NAME, "insert", INSERT)
        }
    }

    override fun onCreate(): Boolean {
        val dbHelper = DBSample(context)
        mDatabase = dbHelper.getWritableDatabase()
        return if (mDatabase == null) false else true
    }

    @Nullable
    override fun query(uri: Uri, @Nullable projection: Array<String>?, @Nullable selection: String?, @Nullable selectionArgs: Array<String>?, @Nullable sortOrder: String?): Cursor? {
        var sortOrder = sortOrder
        Log.d("test", "uri : $uri")
        val qb = SQLiteQueryBuilder()
        qb.tables = "my_table"
        //switch (uriMatcher.match(uri)) {
        //  case STUDENTS:
        //    qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
        //  break;
        // case STUDENT_ID:
        //   qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
        //  break;
        //  default:
        // }
        qb.setProjectionMap(STUDENTS_PROJECTION_MAP)

        if (sortOrder == null || sortOrder === "") {
            sortOrder = /*NAME*/ "contents"
        }

        val c = qb.query(mDatabase, projection, selection, selectionArgs, null, null, sortOrder)
        c.setNotificationUri(context!!.contentResolver, uri)
        return c
    }

    @Nullable
    override fun getType(uri: Uri): String? {
        return null
    }

    @Nullable
    override fun insert(uri: Uri, @Nullable values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, @Nullable selection: String?, @Nullable selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun update(uri: Uri, @Nullable values: ContentValues?, @Nullable selection: String?, @Nullable selectionArgs: Array<String>?): Int {
        return 0
    }
}