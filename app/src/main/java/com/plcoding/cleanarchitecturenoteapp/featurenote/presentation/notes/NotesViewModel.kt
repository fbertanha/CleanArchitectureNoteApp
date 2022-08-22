package com.plcoding.cleanarchitecturenoteapp.featurenote.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.usecase.AddNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.usecase.DeleteNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.usecase.GetNotesUseCase
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.util.NoteOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by felipebertanha on 16/August/2022
 */
@HiltViewModel
class NotesViewModel @Inject() constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var recentlyDeletedNote: Note? = null
    private var getNotesJob : Job? = null

    init {
        getNotes()
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (event.order::class == _state.value.noteOrder::class
                    && event.order.orderType == _state.value.noteOrder.orderType
                ) return
                getNotes(event.order)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    deleteNoteUseCase(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    addNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = _state.value.isOrderSectionVisible.not()
                )
            }
        }
    }

    private fun getNotes(order: NoteOrder = NoteOrder.Date()) {
        getNotesJob?.cancel()
        getNotesJob = getNotesUseCase(order)
            .onEach { notes ->
                _state.value = _state.value.copy(
                    notes = notes,
                    noteOrder = order
                )
            }.launchIn(viewModelScope)
    }
}