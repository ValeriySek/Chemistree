package com.selflearning.chemistree.feature.f_games.gamespack.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import com.selflearning.chemistree.core.adapter.BindableItemController
import com.selflearning.chemistree.core.adapter.BindableViewHolder
import com.selflearning.chemistree.databinding.ListItemGamesCardBinding
import com.selflearning.chemistree.feature.f_games.gamespack.data.GamesRowType

class GamesController(
        val clickListener: (Int) -> Unit
) : BindableItemController() {

    override fun createViewHolder(parent: ViewGroup): BindableViewHolder {
        val binding = ListItemGamesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    inner class Holder(val binding: ListItemGamesCardBinding) :
            BindableViewHolder(binding.root) {

        private lateinit var item: GamesRowType

        init {
            itemView.setOnClickListener {
                clickListener(item.id)
            }
        }

        override fun bind(data: Any) {
            item = data as GamesRowType
//            Picasso.get().load(item.background).into(binding.ivBackgroundGameCard)
            binding.tvNameGameCard.text = item.name
        }
    }
}