package com.plcoding.cleanarchitecturenoteapp.featurenote.domain.util

/**
 * Created by felipebertanha on 16/August/2022
 */
sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
