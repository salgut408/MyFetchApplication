package com.sgut.android.myfetchapplication.data.dto_mappers

interface DomainMapperInterface<T, DomainModel> {
    fun mapToDomainModel(model: T): DomainModel
}