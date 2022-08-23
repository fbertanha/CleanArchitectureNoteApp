package com.plcoding.cleanarchitecturenoteapp.featurenote.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.dto.NoteDto

/**
 * Created by felipebertanha on 15/August/2022
 */
@Database(
    entities = [NoteDto::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}