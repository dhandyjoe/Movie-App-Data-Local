package com.example.movieapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.movieapp.model.ModelMovie

class DatabaseHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 2
        private val DATABASE_NAME = "movieku.db"
        private val TABLE_NAME = "FavoriteMovie"

        // all coloumn database
        private val COLOUMN_ID = "_id"
        private val COLOUMN_JUDUL = "_judul"
        private val COLOUMN_DESC = "_desc"
        private val COLOUMN_GENRE = "_genre"
        private val COLOUMN_POSTER = "_poster"
        private val COLOUMN_TRAILER = "_trailer"
        private val COLOUMN_RATING = "_rating"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val create = ("CREATE TABLE " + TABLE_NAME + "(" +
                COLOUMN_ID + " INTEGER PRIMARY KEY, " +
                COLOUMN_JUDUL + " TEXT, " +
                COLOUMN_DESC + " TEXT, " +
                COLOUMN_GENRE + " TEXT, " +
                COLOUMN_POSTER + " INTEGER, " +
                COLOUMN_TRAILER + " INTEGER, " +
                COLOUMN_RATING + " FLOAT)")
        p0?.execSQL(create)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE " + TABLE_NAME)
        onCreate(p0)
    }

    fun addFavorite(modelMovie: ModelMovie): Long {
        val db = this.writableDatabase
        val content = ContentValues()

        content.put(COLOUMN_ID, modelMovie.id)
        content.put(COLOUMN_JUDUL, modelMovie.judul)
        content.put(COLOUMN_DESC, modelMovie.desc)
        content.put(COLOUMN_GENRE, modelMovie.genre)
        content.put(COLOUMN_POSTER, modelMovie.poster)
        content.put(COLOUMN_TRAILER, modelMovie.trailer)
        content.put(COLOUMN_RATING, modelMovie.rating)

        val result = db.insert(TABLE_NAME, null, content)
        db.close()
        return result
    }

    fun deleteFavorite(modelMovie: ModelMovie): Int {
        val db = writableDatabase
        val result = db.delete(TABLE_NAME, COLOUMN_ID + "=" + modelMovie.id, null)
        db.close()
        return result
    }

    fun queryById(id: String): Cursor {
        val db = readableDatabase
        return db.query(
            TABLE_NAME,
            null,
                "$COLOUMN_ID=?",
             arrayOf(id),
            null,
            null,
            null,
            null
        )
    }
    
    fun getFavoriteMovie(): ArrayList<ModelMovie>{
        val favoriteMovie = ArrayList<ModelMovie>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"

        val cursor: Cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()) {
            do {
                val place = ModelMovie(
                        cursor.getInt(cursor.getColumnIndex(COLOUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLOUMN_JUDUL)),
                        cursor.getString(cursor.getColumnIndex(COLOUMN_DESC)),
                        cursor.getString(cursor.getColumnIndex(COLOUMN_GENRE)),
                        cursor.getInt(cursor.getColumnIndex(COLOUMN_POSTER)),
                        cursor.getInt(cursor.getColumnIndex(COLOUMN_TRAILER)),
                        cursor.getFloat(cursor.getColumnIndex(COLOUMN_RATING))
                )
                favoriteMovie.add(place)
            } while (cursor.moveToNext())
        }
        return favoriteMovie
    }
}
