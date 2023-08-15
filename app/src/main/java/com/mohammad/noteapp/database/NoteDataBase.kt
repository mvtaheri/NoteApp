package com.mohammad.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohammad.noteapp.database.NoteDAO
import com.mohammad.noteapp.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDAO

    companion object {
        @Volatile
        private var instance: NoteDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDataBase::class.java,
                "note_db"
            ).build()

    }


}