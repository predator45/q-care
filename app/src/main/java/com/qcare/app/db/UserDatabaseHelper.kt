package com.qcare.app.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "qcare.db"
        private const val DATABASE_VERSION = 4 // ⬅️ NAIKKAN VERSION (WAJIB)

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

        // ================== AKUN DEFAULT ==================

        // 1️⃣ AKUN DOKTER
        val dokter = ContentValues().apply {
            put(COL_EMAIL, "dokter@qcare.com")
            put(COL_PASSWORD, "dokter123")
            put(COL_ROLE, "dokter")
            put(COL_FULLNAME, "Dokter QCare")
            put(COL_PHONE, "081234567890")
            put(COL_ADDRESS, "Klinik QCare")
        }
        db.insert(TABLE_USER, null, dokter)

        // 2️⃣ AKUN PASIEN
        val pasien = ContentValues().apply {
            put(COL_EMAIL, "pasien@qcare.com")
            put(COL_PASSWORD, "pasien123")
            put(COL_ROLE, "pasien")
            put(COL_FULLNAME, "Pasien QCare")
            put(COL_PHONE, "089876543210")
            put(COL_ADDRESS, "Bandung")
        }
        db.insert(TABLE_USER, null, pasien)
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
            put(COL_FULLNAME, fullName)
            put(COL_PHONE, phone)
            put(COL_ADDRESS, address)
        }
        val result = db.update(
            TABLE_USER,
            values,
            "$COL_ID = ?",
            arrayOf(userId.toString())
        )
        db.close()
        return result > 0
    }
}
