package com.plcoding.cleanarchitecturenoteapp.featurenote.domain.usecase

import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.exception.NoteNotFoundException
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.repository.NoteRepository
import javax.inject.Inject

/**
 * Created by felipebertanha on 19/August/2022
 */
class GetNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note {
        return repository.getNoteById(id)
            ?: throw NoteNotFoundException("Not found any Note with the '$id' id")
    }
}