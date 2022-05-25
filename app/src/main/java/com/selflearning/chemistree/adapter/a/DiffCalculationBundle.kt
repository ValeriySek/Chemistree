package com.example.adapter.a

import com.selflearning.chemistree.adapter.a.ItemList

internal data class DiffCalculationBundle(
    val items: ItemList,
    val oldItemInfo: List<ItemInfo>,
    val newItemInfo: List<ItemInfo>
)