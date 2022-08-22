package com.plcoding.cleanarchitecturenoteapp.featurenote.domain.util

/**
 * Created by felipebertanha on 16/August/2022
 */
sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType = OrderType.Ascending) : NoteOrder(orderType)
    class Date(orderType: OrderType = OrderType.Descending) : NoteOrder(orderType)
    object Color : NoteOrder(OrderType.Ascending)
}

fun NoteOrder.copy(orderType: OrderType) =
    when (this) {
        is NoteOrder.Title -> NoteOrder.Title(orderType)
        is NoteOrder.Date -> NoteOrder.Date(orderType)
        is NoteOrder.Color -> NoteOrder.Color
    }

