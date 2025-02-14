package com.plcoding.cleanarchitecturenoteapp.featurenote.domain.model

import com.plcoding.cleanarchitecturenoteapp.ui.theme.*

/**
 * Created by felipebertanha on 15/August/2022
 */
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}