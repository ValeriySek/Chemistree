package com.example.adapter.a

import androidx.recyclerview.widget.DiffUtil
import com.selflearning.chemistree.adapter.a.ItemList

internal data class DiffResultBundle(
    val diffResult: DiffUtil.DiffResult?,
    val calculationBundle: DiffCalculationBundle
) {

    /**
     * @see DiffCalculationBundle.items
     */
    val items: ItemList
        get() = calculationBundle.items

    /**
     * @see DiffCalculationBundle.newItemInfo
     */
    val newItemInfo: List<ItemInfo>
        get() = calculationBundle.newItemInfo
}