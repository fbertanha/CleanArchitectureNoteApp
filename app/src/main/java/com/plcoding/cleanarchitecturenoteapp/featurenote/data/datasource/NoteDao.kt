package com.plcoding.cleanarchitecturenoteapp.featurenote.data.datasource

import androidx.room.*
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Created by felipebertanha on 15/August/2022
 */
@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note where id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}