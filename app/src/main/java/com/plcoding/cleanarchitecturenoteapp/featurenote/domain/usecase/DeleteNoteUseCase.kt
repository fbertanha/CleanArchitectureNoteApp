package com.plcoding.cleanarchitecturenoteapp.featurenote.domain.usecase

import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.repository.NoteRepository
import javax.inject.Inject

/**
 * Created by felipebertanha on 16/August/2022
 */
class DeleteNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}