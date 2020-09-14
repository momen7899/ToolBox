package com.example.toolbox

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.widget.Toast
import java.io.ByteArrayOutputStream

class DatabaseOpenHelper(val context: Context) : SQLiteOpenHelper(context, "pass.db", null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE pass_list (" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " app_name TEXT," +
                    " pass TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS pass_list")
    }

    fun addPass(password: Password) {
        val value = ContentValues()
        value.put("app_name", password.appName)
        value.put("pass", password.pass)
        val result = writableDatabase.insert("pass_list", null, value)
        if (result > 0)
            Toast.makeText(context, context.getString(R.string.doneDB), Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, context.getString(R.string.wrongDB), Toast.LENGTH_SHORT).show()
        writableDatabase.close()
    }

    fun updatePass(password: Password) {
        val value = ContentValues()
        value.put("app_name", password.appName)
        value.put("pass", password.pass)
        val result =
            writableDatabase.update("pass_list", value, "id='${password.id}'", null)
        if (result > 0)
            Toast.makeText(context, context.getString(R.string.doneDB), Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, context.getString(R.string.wrongDB), Toast.LENGTH_SHORT).show()
        readableDatabase.close()
    }

    @SuppressLint("Recycle")
    fun getPass(id: Int): Password {
        val cursor: Cursor =
            readableDatabase.rawQuery(
                "SELECT * FROM pass_list WHERE id = '$id' ",
                null
            )
        cursor.moveToFirst()
        val name = cursor.getString(1)
        val pass = cursor.getString(2)
        return Password(name, id, pass)
    }

    @SuppressLint("Recycle")
    fun getPassList(): ArrayList<Password> {
        val cursor: Cursor =
            readableDatabase.rawQuery("SELECT * FROM pass_list", null)
        cursor.moveToFirst()
        var i = 0
        val list: ArrayList<Password> = ArrayList()
        while (cursor.count > i) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val pass = cursor.getString(2)
            list.add(Password(name, id, pass))
            cursor.moveToNext()
            i++
        }
        cursor.close()
        readableDatabase.close()
        return list
    }


}