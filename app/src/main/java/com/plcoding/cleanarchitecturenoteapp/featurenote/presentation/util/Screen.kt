package com.plcoding.cleanarchitecturenoteapp.featurenote.presentation.util

/**
 * Created by felipebertanha on 20/August/2022
 */
sealed class Screen(val route: String) {
    object NotesScreen : Screen("noteScreen")
    object AddEditNoteScreen : Screen("addEditNoteScreen")
}
