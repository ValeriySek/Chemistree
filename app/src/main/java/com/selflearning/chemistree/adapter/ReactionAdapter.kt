package com.selflearning.chemistree.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selflearning.chemistree.R
import com.selflearning.chemistree.adapter.ReactionAdapter.ReactionViewHolder
import com.selflearning.chemistree.chemistry.reactions.ReactionEntry
import com.selflearning.chemistree.databinding.RvReactionsBinding

class ReactionAdapter(private val reactionEntryList: List<ReactionEntry>?) : RecyclerView.Adapter<ReactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReactionViewHolder {
        val binding = RvReactionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReactionViewHolder, position: Int) {
        if (reactionEntryList != null) {
            val reaction: ReactionEntry = reactionEntryList[position]
            holder.bind(reaction)
        }
    }

    override fun getItemCount(): Int {
        return reactionEntryList!!.size
    }

    inner class ReactionViewHolder(private val binding: RvReactionsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reaction: ReactionEntry){
            val react = reaction.reaction.replace("([a-zA-Z])([0-9]*)".toRegex(), "$1<sub><small>$2</small></sub>")
            binding.tvReaction.text = Html.fromHtml(react)
        }
    }
}