@file:OptIn(ExperimentalAnimationApi::class)

package com.plcoding.cleanarchitecturenoteapp.featurenote.presentation.notes.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.util.OrderType
import com.plcoding.cleanarchitecturenoteapp.featurenote.domain.util.copy

/**
 * Created by felipebertanha on 16/August/2022
 */
@Composable
fun OrderSection(
    noteOrder: NoteOrder,
    onOrderChanged: (NoteOrder) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Title",
                selected = noteOrder is NoteOrder.Title,
                onClick = { onOrderChanged(NoteOrder.Title(noteOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = noteOrder is NoteOrder.Date,
                onClick = { onOrderChanged(NoteOrder.Date(noteOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = noteOrder is NoteOrder.Color,
                onClick = { onOrderChanged(NoteOrder.Color) }
            )
        }
        AnimatedVisibility(
            visible = noteOrder !is NoteOrder.Color,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Row(modifier.fillMaxWidth()) {
                DefaultRadioButton(
                    text = "Ascending",
                    selected = noteOrder.orderType is OrderType.Ascending,
                    onClick = { onOrderChanged(noteOrder.copy(OrderType.Ascending)) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                DefaultRadioButton(
                    text = "Descending",
                    selected = noteOrder.orderType is OrderType.Descending,
                    onClick = { onOrderChanged(noteOrder.copy(OrderType.Descending)) }
                )
            }
        }
    }

}