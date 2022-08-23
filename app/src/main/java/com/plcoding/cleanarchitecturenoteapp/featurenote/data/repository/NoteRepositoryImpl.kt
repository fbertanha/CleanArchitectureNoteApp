package com.plcoding.cleanarchitecturenoteapp.featurenote.data.repository

import com.plcoding.cleanarchitecturenoteapp.featurenote.data.datasource.NoteDao
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.mapper.Mapper
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.dto.NoteDto
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by felipebertanha on 15/August/2022
 */
class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
    private val mapper: Mapper<Note, NoteDto>
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes().map { list ->
            list.map { note -> mapper.mapToEntity(note) }
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)?.let {
            mapper.mapToEntity(it)
        }
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(mapper.mapFromEntity(note))
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(mapper.mapFromEntity(note))
    }
}