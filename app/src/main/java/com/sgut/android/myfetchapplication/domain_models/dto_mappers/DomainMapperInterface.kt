package com.sgut.android.myfetchapplication.domain_models.dto_mappers

interface DomainMapperInterface<T, DomainModel> {
    fun mapToDomainModel(model: T): DomainModel
}