package com.selflearning.chemistree.feature.f_games.gamespack.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import com.selflearning.chemistree.core.adapter.BindableItemController
import com.selflearning.chemistree.core.adapter.BindableViewHolder
import com.selflearning.chemistree.databinding.ListItemGamePackDividerBinding
import com.selflearning.chemistree.feature.f_games.gamespack.data.DividerRowType

class GamesDividerController : BindableItemController() {

    override fun createViewHolder(parent: ViewGroup): BindableViewHolder {
        val binding = ListItemGamePackDividerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    inner class Holder(val binding: ListItemGamePackDividerBinding) :
            BindableViewHolder(binding.root) {

        override fun bind(data: Any) {
            val item = data as DividerRowType
            binding.gamesItemDividerTv.text = item.text
        }
    }
}