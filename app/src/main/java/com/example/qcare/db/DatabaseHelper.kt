package com.example.qcare

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "qcare.db"
        private const val DATABASE_VERSION = 2   // ⬅️ NAIKKAN VERSION

        private const val TABLE_USER = "users"
        private const val COL_ID = "id"
        private const val COL_EMAIL = "email"
        private const val COL_PASSWORD = "password"
        private const val COL_ROLE = "role"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_USER (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_EMAIL TEXT UNIQUE,
                $COL_PASSWORD TEXT,
                $COL_ROLE TEXT
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    // ✅ REGISTER DENGAN ROLE
    fun register(email: String, password: String, role: String): Boolean {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COL_EMAIL, email)
            put(COL_PASSWORD, password)
            put(COL_ROLE, role)
        }

        val result = db.insert(TABLE_USER, null, values)
        db.close()

        return result != -1L
    }

    // ✅ LOGIN (CEK EMAIL & PASSWORD)
    fun login(email: String, password: String): Boolean {
        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USER WHERE $COL_EMAIL=? AND $COL_PASSWORD=?",
            arrayOf(email, password)
        )

        val exists = cursor.count > 0
        cursor.close()
        db.close()

        return exists
    }

    // ✅ AMBIL ROLE SAAT LOGIN (PENTING)
    fun getUserRole(email: String): String? {
        val db = readableDatabase
        var role: String? = null

        val cursor = db.rawQuery(
            "SELECT $COL_ROLE FROM $TABLE_USER WHERE $COL_EMAIL=?",
            arrayOf(email)
        )

        if (cursor.moveToFirst()) {
            role = cursor.getString(0)
        }

        cursor.close()
        db.close()
        return role
    }
}
