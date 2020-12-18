package com.selflearning.chemistree.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.selflearning.chemistree.R
import com.selflearning.chemistree.databinding.ItemGamesCardBinding
import com.selflearning.chemistree.databinding.RvGamePackDividerBinding
import com.selflearning.chemistree.f_games.gamespack.DividerRowType
import com.selflearning.chemistree.f_games.gamespack.GamesRowType
import com.selflearning.chemistree.f_games.gamespack.RowType
import com.selflearning.chemistree.listeners.OnItemClickListener
import com.squareup.picasso.Picasso

class GamesPackAdapter(private val rowTypeList: List<RowType>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var clickListener: OnItemClickListener? = null
    private val TYPE_ITEM1 = 0
    private val TYPE_ITEM2 = 1

    fun setClickListener(clickListener: OnItemClickListener?) {
        this.clickListener = clickListener
    }

    override fun getItemViewType(position: Int): Int {
        return if (rowTypeList[position] is DividerRowType) {
            TYPE_ITEM1
        } else TYPE_ITEM2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM1) {
            val binding = RvGamePackDividerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            DividerViewHolder(binding)
        } else {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_game_pack_games, parent, false);
            val binding = ItemGamesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            GamePackViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val type = rowTypeList[position]
        if (holder is DividerViewHolder) {
            holder.bind(type as DividerRowType)
        } else if (holder is GamePackViewHolder) {
//            ((GamePackViewHolder) holder).ivBackground.setImageResource(((GamesRowType)type).getBackground());
            holder.bind(type as GamesRowType)
        }
    }

    override fun getItemCount(): Int {
        return rowTypeList.size
    }

    internal inner class DividerViewHolder(private val binding: RvGamePackDividerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(type: DividerRowType) {
            binding.tvRVGamesDivider.text = type.text
        }
    }

    internal inner class GamePackViewHolder(private val binding: ItemGamesCardBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var ivIcon: ImageView
        override fun onClick(v: View) {
            clickListener?.onItemClick(adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
            ivIcon = itemView.findViewById(R.id.ivIconGameCard)
        }

        fun bind(type: GamesRowType) {
            Picasso.get().load(type.background).fit().centerCrop().into(binding.ivBackgroundGameCard)
            binding.tvNameGameCard.text = type.name
            binding.cardViewGame.setOnClickListener(type.onClickListener)
        }
    }
}