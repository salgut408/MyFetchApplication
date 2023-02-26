package com.sgut.android.myfetchapplication.utils

import com.sgut.android.myfetchapplication.domain_models.ItemDomainModel

class ItemComparator {
    companion object: Comparator<ItemDomainModel> {
        override fun compare(firstItem: ItemDomainModel, secondItem: ItemDomainModel): Int = when {
            firstItem.listId != secondItem.listId -> firstItem.listId!! - secondItem.listId!!
            firstItem.id != secondItem.id -> firstItem.id!! - secondItem.id!!
            else -> 0
        }
    }
}