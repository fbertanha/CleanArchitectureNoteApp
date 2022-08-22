package com.plcoding.cleanarchitecturenoteapp.featurenote.domain.usecase

import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by felipebertanha on 16/August/2022
 */
class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(
        order: NoteOrder = NoteOrder.Date()
    ): Flow<List<Note>> {
        val isAscending = order.orderType == OrderType.Ascending
        return repository.getNotes().map { notes ->
            when (order) {
                is NoteOrder.Title -> sortByTitle(isAscending, notes)
                is NoteOrder.Date -> sortByCreatedDate(isAscending, notes)
                is NoteOrder.Color -> sortByColor(notes)
            }
        }
    }

    private fun sortByColor(notes: List<Note>) =
        notes.sortedBy { it.color }

    private fun sortByCreatedDate(
        isAscending: Boolean,
        notes: List<Note>
    ) =
        if (isAscending) notes.sortedBy { it.timestamp } else notes.sortedByDescending { it.timestamp }

    private fun sortByTitle(
        isAscending: Boolean,
        notes: List<Note>
    ) =
        if (isAscending) notes.sortedBy { it.title.lowercase() } else notes.sortedByDescending { it.title.lowercase() }
}