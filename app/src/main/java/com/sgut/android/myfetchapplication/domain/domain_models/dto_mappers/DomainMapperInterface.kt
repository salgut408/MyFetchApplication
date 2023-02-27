package com.sgut.android.myfetchapplication.domain.domain_models.dto_mappers

interface DomainMapperInterface<T, DomainModel> {
    fun mapToDomainModel(model: T): DomainModel
}