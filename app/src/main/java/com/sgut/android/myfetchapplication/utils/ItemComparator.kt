package com.sgut.android.myfetchapplication.utils

import com.sgut.android.myfetchapplication.data.domain_models.ItemDomainModel

class ItemComparator {
    companion object: Comparator<ItemDomainModel> {
        override fun compare(a: ItemDomainModel?, b: ItemDomainModel?): Int = when {
            a?.listId != b?.listId -> a?.listId!! - b?.listId!!
            a?.id != b?.id -> a?.id!! - b?.id!!
            else -> 0
        }
    }
}