package com.example.adapter.a

import androidx.recyclerview.widget.DiffUtil.Callback

internal fun interface DiffCallbackCreator {

    /**
     * Create [Callback].
     */
    fun createDiffCallback(
        oldItemInfo: List<ItemInfo>,
        newItemInfo: List<ItemInfo>
    ): Callback
}