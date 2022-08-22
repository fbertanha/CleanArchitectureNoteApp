package com.plcoding.cleanarchitecturenoteapp.featurenote.presentation.addnote

/**
 * Created by felipebertanha on 19/August/2022
 */
data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)