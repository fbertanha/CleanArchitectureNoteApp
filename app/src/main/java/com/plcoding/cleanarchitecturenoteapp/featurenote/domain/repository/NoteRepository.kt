package com.plcoding.cleanarchitecturenoteapp.featurenote.domain.repository

import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Created by felipebertanha on 15/August/2022
 */
interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}