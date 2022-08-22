package com.plcoding.cleanarchitecturenoteapp.featurenote.presentation.addnote.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by felipebertanha on 20/August/2022
 */
@Composable
fun ColorBubble(
    color: Color,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .shadow(15.dp, CircleShape)
            .clip(CircleShape)
            .background(color)
            .border(
                width = 3.dp,
                color = if (selected) Color.Black else Color.Transparent,
                shape = CircleShape
            )
            .clickable {
                onClick()
            }
    )

}