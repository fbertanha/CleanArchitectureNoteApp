package com.plcoding.cleanarchitecturenoteapp.featurenote.data.mapper

/**
 * Created by felipebertanha on 22/August/2022
 */
interface Mapper<E, D> {

    fun mapFromEntity(entity: E) : D

    fun mapToEntity(data: D) : E
}