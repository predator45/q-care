package com.example.qcare.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "qcare.db"
        private const val DATABASE_VERSION = 3   // ⬅️ WAJIB NAIK

        const val TABLE_USER = "users"

        const val COL_ID = "id"
        const val COL_EMAIL = "email"
        const val COL_PASSWORD = "password"
        const val COL_ROLE = "role"
        const val COL_FULLNAME = "full_name"
        const val COL_PHONE = "phone"
        const val COL_ADDRESS = "address"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_USER (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_EMAIL TEXT UNIQUE,
                $COL_PASSWORD TEXT,
                $COL_ROLE TEXT,
                $COL_FULLNAME TEXT,
                $COL_PHONE TEXT,
                $COL_ADDRESS TEXT
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    // ================= REGISTER =================
    fun register(email: String, password: String, role: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_EMAIL, email)
            put(COL_PASSWORD, password)
            put(COL_ROLE, role)
            put(COL_FULLNAME, "")
            put(COL_PHONE, "")
            put(COL_ADDRESS, "")
        }

        val result = db.insert(TABLE_USER, null, values)
        db.close()
        return result != -1L
    }

    // ================= LOGIN =================
    fun login(email: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USER WHERE $COL_EMAIL=? AND $COL_PASSWORD=?",
            arrayOf(email, password)
        )
        val success = cursor.count > 0
        cursor.close()
        db.close()
        return success
    }

    // ================= GET ROLE =================
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

    // ================= GET USER =================
    fun getUserByEmail(email: String): Cursor {
        val db = readableDatabase
        return db.rawQuery(
            "SELECT * FROM $TABLE_USER WHERE $COL_EMAIL=?",
            arrayOf(email)
        )
    }

    // ================= UPDATE PROFILE =================
    fun updateUserById(
        userId: Int,
        fullName: String,
        phone: String,
        address: String
    ): Boolean {

        val db = writableDatabase
        val values = ContentValues().apply {
            put("full_name", fullName)
            put("phone", phone)
            put("address", address)
        }

        val result = db.update(
            "users",
            values,
            "id = ?",
            arrayOf(userId.toString())
        )

        db.close()
        return result > 0
    }
}
