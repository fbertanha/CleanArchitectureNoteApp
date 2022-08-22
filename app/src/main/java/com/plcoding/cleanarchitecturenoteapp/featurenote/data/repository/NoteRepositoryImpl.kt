package com.plcoding.cleanarchitecturenoteapp.featurenote.data.repository

import com.plcoding.cleanarchitecturenoteapp.featurenote.data.datasource.NoteDao
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by felipebertanha on 15/August/2022
 */
class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}