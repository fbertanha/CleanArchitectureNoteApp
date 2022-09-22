package com.plcoding.cleanarchitecturenoteapp.featurenote.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.exception.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.repository.NoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by felipebertanha on 18/September/2022
 */
class AddNoteUseCaseTest {
    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var fakeRepository: NoteRepository

    @Before
    fun setUp() {
        fakeRepository = FakeNoteRepository()
        addNoteUseCase = AddNoteUseCase(fakeRepository)
    }

    @Test
    fun `Add note with empty title, throws exception`() {
        val note = Note(
            title = "",
            content = "content",
            timestamp = 0L,
            color = 0
        )
        val exception = Assert.assertThrows(InvalidNoteException::class.java) {
            runBlocking { addNoteUseCase(note) }
        }
        assertThat(exception).hasMessageThat().isEqualTo("Note title can't be empty.")
    }

    @Test
    fun `Add note with empty content, throws exception`() {
        val note = Note(
            title = "title",
            content = "",
            timestamp = 0L,
            color = 0
        )

        val exception = Assert.assertThrows(InvalidNoteException::class.java) {
            runBlocking { addNoteUseCase(note) }
        }

        assertThat(exception).hasMessageThat().isEqualTo("Note content can't be empty.")
    }

    @Test
    fun `Add note with title and content filled, insert on database`() = runBlocking {
        val validNote = Note(
            title = "title",
            content = "content",
            timestamp = 0L,
            color = 0,
            id = 1
        )

        addNoteUseCase(validNote)

        val noteInsertedOnDatabase = fakeRepository.getNoteById(1)

        assertThat(validNote).isEqualTo(noteInsertedOnDatabase)
    }

}