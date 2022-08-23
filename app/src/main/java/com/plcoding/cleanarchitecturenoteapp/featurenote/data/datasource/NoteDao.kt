package com.plcoding.cleanarchitecturenoteapp.featurenote.data.datasource

import androidx.room.*
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.dto.NoteDto
import kotlinx.coroutines.flow.Flow

/**
 * Created by felipebertanha on 15/August/2022
 */
@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<NoteDto>>

    @Query("SELECT * FROM notes where id = :id")
    suspend fun getNoteById(id: Int): NoteDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteDto)

    @Delete
    suspend fun deleteNote(note: NoteDto)
}