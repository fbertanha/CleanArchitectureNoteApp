package com.plcoding.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.datasource.NoteDao
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.datasource.NoteDatabase
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.mapper.Mapper
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.mapper.NoteMapper
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.dto.NoteDto
import com.plcoding.cleanarchitecturenoteapp.featurenote.data.repository.NoteRepositoryImpl
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by felipebertanha on 16/August/2022
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao
    }

    @Provides
    @Singleton
    fun provideNoteRepository(dao: NoteDao, noteMapper: Mapper<Note, NoteDto>): NoteRepository {
        return NoteRepositoryImpl(dao, noteMapper)
    }

    @Provides
    @Singleton
    fun provideNoteMapper(): Mapper<Note, NoteDto> {
        return NoteMapper()
    }
}