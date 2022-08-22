package com.plcoding.cleanarchitecturenoteapp.featurenote.domain.usecase

import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.exception.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.repository.NoteRepository
import javax.inject.Inject

/**
 * Created by felipebertanha on 16/August/2022
 */
class AddNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {


    suspend operator fun invoke(note: Note) {
        validateNoteFields(note)
        repository.insertNote(note)
    }

    @Throws(InvalidNoteException::class)
    private fun validateNoteFields(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("Note title can't be empty.")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("Note content can't be empty.")
        }
    }
}