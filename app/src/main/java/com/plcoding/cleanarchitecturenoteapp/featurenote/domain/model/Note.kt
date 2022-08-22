package com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.cleanarchitecturenoteapp.ui.theme.*

/**
 * Created by felipebertanha on 15/August/2022
 */
//TODO
// Create another file to make this Room Entity.
// Domain module shouldn't know about room.
@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey
    val id : Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}