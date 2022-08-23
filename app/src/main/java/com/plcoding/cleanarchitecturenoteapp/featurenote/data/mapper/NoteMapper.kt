package com.plcoding.cleanarchitecturenoteapp.featurenote.data.mapper

import com.plcoding.cleanarchitecturenoteapp.featurenote.data.dto.NoteDto
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model.Note
import javax.inject.Inject

/**
 * Created by felipebertanha on 22/August/2022
 */
class NoteMapper @Inject constructor() : Mapper<Note, NoteDto> {

    override fun mapFromEntity(entity: Note) = NoteDto(
        title = entity.title,
        content = entity.content,
        timestamp = entity.timestamp,
        color = entity.color,
        id = entity.id
    )

    override fun mapToEntity(data: NoteDto) = Note(
        title = data.title,
        content = data.content,
        timestamp = data.timestamp,
        color = data.color,
        id = data.id
    )
}