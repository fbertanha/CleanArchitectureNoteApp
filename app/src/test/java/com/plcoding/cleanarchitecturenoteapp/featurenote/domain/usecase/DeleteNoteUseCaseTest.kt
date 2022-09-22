package com.plcoding.cleanarchitecturenoteapp.featurenote.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by felipebertanha on 18/September/2022
 */
class DeleteNoteUseCaseTest {
    private lateinit var deleteNoteUseCase: DeleteNoteUseCase
    private lateinit var fakeNoteRepository: NoteRepository

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        deleteNoteUseCase = DeleteNoteUseCase(fakeNoteRepository)
    }

    @Test
    fun `Add a new note and then delete it`() = runBlocking {
        val note = Note(
            title = "title",
            content = "content",
            timestamp = 0L,
            color = 0,
            id = 1
        )

        fakeNoteRepository.insertNote(note)
        deleteNoteUseCase(note)
        val notesInDatabase = fakeNoteRepository.getNotes().first()
        assertThat(notesInDatabase).hasSize(0)
    }
}