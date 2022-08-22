package com.plcoding.cleanarchitecturenoteapp.featurenote.presentation.addnote

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.exception.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.exception.NoteNotFoundException
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.usecase.AddNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.usecase.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by felipebertanha on 19/August/2022
 */
@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var currentNoteId: Int? = null

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter title..."
        )
    )
    val noteTitle = _noteTitle

    private val _noteContent = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter some content..."
        )
    )
    val noteContent = _noteContent

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    companion object {
        const val KEY_NOTE_ID = "noteId"
    }

    init {
        savedStateHandle.get<Int>(KEY_NOTE_ID)?.let { noteId ->
            if(noteId != -1) {
                getNote(noteId)
            }
        }

    }

    private fun getNote(noteId: Int) = viewModelScope.launch {
        try {
            val existingNote = getNoteUseCase(noteId)
            currentNoteId = noteId
            _noteTitle.value = _noteTitle.value.copy(text = existingNote.title, isHintVisible = false)
            _noteContent.value = _noteContent.value.copy(text = existingNote.content, isHintVisible = false)
            _noteColor.value = existingNote.color
        } catch (e: NoteNotFoundException) {
            _eventFlow.emit(UiEvent.ShowSnackbar(e.message ?: "Unknown error"))
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = _noteTitle.value.copy(text = event.value)
            }
            is AddEditNoteEvent.TitleFocusChanged -> {
                _noteTitle.value = _noteTitle.value.copy(
                    isHintVisible = _noteTitle.value.text.isBlank()
                            && !event.focusState.isFocused
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(text = event.value)
            }
            is AddEditNoteEvent.ContentFocusChanged -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = _noteContent.value.text.isBlank()
                            && !event.focusState.isFocused
                )
            }
            is AddEditNoteEvent.ColorChanged -> {
                _noteColor.value = event.color
            }
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        addNoteUseCase(
                            Note(
                                id = currentNoteId,
                                title = _noteTitle.value.text,
                                content = _noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = _noteColor.value,
                            )
                        )

                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }
}