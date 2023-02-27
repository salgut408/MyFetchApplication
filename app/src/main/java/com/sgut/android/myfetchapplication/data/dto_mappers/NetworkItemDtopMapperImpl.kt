package com.sgut.android.myfetchapplication.data.dto_mappers

import com.sgut.android.myfetchapplication.domain_models.ItemDomainModel
import com.sgut.android.myfetchapplication.data.remote.network_responses.NetworkItem

class NetworkItemDtoMapperImpl : DomainMapperInterface<NetworkItem, ItemDomainModel>{
    override fun mapToDomainModel(model: NetworkItem): ItemDomainModel {
        return ItemDomainModel(
            id = model.id,
            listId = model.listId,
            name = model.name
        )
    }
    fun toDomainList(initial: List<NetworkItem>): List<ItemDomainModel> {
        return initial.map { mapToDomainModel(it) }
    }
}