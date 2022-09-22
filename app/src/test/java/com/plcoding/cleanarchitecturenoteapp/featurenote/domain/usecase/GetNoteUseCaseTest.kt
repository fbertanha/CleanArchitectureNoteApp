package com.plcoding.cleanarchitecturenoteapp.featurenote.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.exception.NoteNotFoundException
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.repository.NoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

/**
 * Created by felipebertanha on 18/September/2022
 */
class GetNoteUseCaseTest {
    private lateinit var getNoteUseCase: GetNoteUseCase
    private lateinit var fakeRepository: NoteRepository

    @Before
    fun setUp() {
        fakeRepository = FakeNoteRepository()
        getNoteUseCase = GetNoteUseCase(fakeRepository)
    }

    @Test
    fun `Enter an invalid note id, throws exception`() {
        val exception = assertThrows(NoteNotFoundException::class.java) {
            runBlocking { getNoteUseCase(id = 100) }
        }

        assertThat(exception).hasMessageThat().isEqualTo("Not found any Note with id '100'")
    }

    @Test
    fun `Add a new note and then get it from database`() = runBlocking {
        val note = Note(
            title = "title",
            content = "content",
            timestamp = 0L,
            color = 0,
            id = 1
        )

        fakeRepository.insertNote(note)
        val noteFromDatabase = getNoteUseCase(id = 1)
        assertThat(note).isEqualTo(noteFromDatabase)
    }
}