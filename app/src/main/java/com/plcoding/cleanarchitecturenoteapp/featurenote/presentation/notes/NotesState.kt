package com.plcoding.cleanarchitecturenoteapp.featurenote.presentation.notes

import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.util.NoteOrder

/**
 * Created by felipebertanha on 16/August/2022
 */
data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(),
    val isOrderSectionVisible: Boolean = false
)
