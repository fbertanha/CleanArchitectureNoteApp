package com.plcoding.cleanarchitecturenoteapp.featurenote.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.cleanarchitecturenoteapp.ui.theme.*

/**
 * Created by felipebertanha on 22/August/2022
 */
@Entity(tableName = "notes")
data class NoteDto(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey
    val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}