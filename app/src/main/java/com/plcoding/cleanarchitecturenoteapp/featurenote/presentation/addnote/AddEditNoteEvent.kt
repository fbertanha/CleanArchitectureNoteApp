package com.plcoding.cleanarchitecturenoteapp.featurenote.presentation.addnote

import androidx.compose.ui.focus.FocusState

/**
 * Created by felipebertanha on 19/August/2022
 */
sealed class AddEditNoteEvent {
    data class EnteredTitle(val value: String) : AddEditNoteEvent()
    data class TitleFocusChanged(val focusState: FocusState) : AddEditNoteEvent()
    data class EnteredContent(val value: String) : AddEditNoteEvent()
    data class ContentFocusChanged(val focusState: FocusState) : AddEditNoteEvent()
    data class ColorChanged(val color: Int) : AddEditNoteEvent()
    object SaveNote : AddEditNoteEvent()
}
