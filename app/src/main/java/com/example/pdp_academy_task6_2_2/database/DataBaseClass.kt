package com.example.pdp_academy_task6_2_2.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pdp_academy_task6_2_2.models.MovieData
import java.util.jar.Attributes.Name

class DataBaseClass(context: Context):SQLiteOpenHelper(
    context, DB_NAME, null, DB_VERSION), DataBaseService{

    companion object{
        const val DB_NAME = "movie_info"
        const val DB_VERSION = 1

        const val TABLE_NAME = "movies"
        const val MOVIE_ID = "id"
        const val MOVIE_NAME = "name"
        const val MOVIE_AUTHOR = "author"
        const val MOVIE_ABOUT = "about"
        const val MOVIE_DATA = "data"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "create table $TABLE_NAME($MOVIE_ID integer not null primary key autoincrement unique, $MOVIE_NAME text not null, $MOVIE_AUTHOR text not null, $MOVIE_ABOUT text not null, $MOVIE_DATA text not null)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addMovie(movieData: MovieData) {
        val database = this.writableDatabase
        val contentVal = ContentValues()
        contentVal.put(MOVIE_NAME, movieData.movieName)
        contentVal.put(MOVIE_AUTHOR, movieData.movieAuthor)
        contentVal.put(MOVIE_ABOUT, movieData.aboutMovie)
        contentVal.put(MOVIE_DATA, movieData.movieDate)

        database.insert(TABLE_NAME, null, contentVal)
        database.close()
    }

    override fun updateMovie(movieData: MovieData): Int {
        val database = this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put(MOVIE_NAME, movieData.movieName)
        contentValues.put(MOVIE_AUTHOR, movieData.movieAuthor)
        contentValues.put(MOVIE_ABOUT, movieData.aboutMovie)
        contentValues.put(MOVIE_DATA, movieData.movieDate)

        return database.update(
            TABLE_NAME, contentValues, "$MOVIE_ID=?", arrayOf("${movieData.id}")
        )
    }

    override fun deleteMovie(movieData: MovieData) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$MOVIE_ID = ?", arrayOf("${movieData.id}"))
        database.close()
    }

    @SuppressLint("Recycle")
    override fun allMovie(): List<MovieData> {
        val list = ArrayList<MovieData>()
        val database = this.readableDatabase
        val query = "select * from $TABLE_NAME"
        val cursor =database.rawQuery(query, null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val author = cursor.getString(2)
                val about = cursor.getString(3)
                val data = cursor.getString(4)
                val movieData = MovieData(id, name, author, about, data)
                list.add(movieData)
            }while (cursor.moveToNext())
        }
        return list
    }

}