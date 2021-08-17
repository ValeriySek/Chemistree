package com.selflearning.chemistree.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.selflearning.chemistree.R
import com.selflearning.chemistree.databinding.RvGame2Binding
import com.selflearning.chemistree.databinding.RvGame3Binding
import com.selflearning.chemistree.listeners.OnItemClickListener
import java.util.*

class GameButtonsAdapter(viewType: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var onItemClickListener: OnItemClickListener
    private lateinit var list: List<String>
    private lateinit var booleans: List<Boolean>
    private val TYPE_ITEM1 = 0
    private val TYPE_ITEM2 = 1
    private val type: Int
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (type == TYPE_ITEM1) {
            val binding = RvGame2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
            GameButtonViewHolder(binding)
        } else {
            val binding = RvGame3Binding.inflate(LayoutInflater.from(parent.context), parent, false)
            GameTextViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val s = list[position]
        if (holder is GameButtonViewHolder) {
            holder.bind(s)
        } else if (holder is GameTextViewHolder) {
            holder.bind(s, booleans[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getList(): List<String> {
        return list
    }

    fun setList(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun getBooleans(): List<Boolean> {
        return booleans
    }

    fun setBooleans(booleans: List<Boolean>) {
        this.booleans = booleans
        notifyDataSetChanged()
    }

    internal inner class GameButtonViewHolder(private val binding: RvGame2Binding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        override fun onClick(v: View) {
            onItemClickListener.onItemClick(adapterPosition)
        }

        init {
            binding.bRVGame2.setOnClickListener(this)
        }

        fun bind(name: String) {
            binding.bRVGame2.text = Html.fromHtml(name)
        }
    }

    internal inner class GameTextViewHolder(private val binding: RvGame3Binding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        override fun onClick(v: View) {
            onItemClickListener.onItemClick(adapterPosition)
        }

        init {
            binding.tvRVGame3.setOnClickListener(this)
        }

        fun bind(name: String, boolean: Boolean) {
            binding.tvRVGame3.text = Html.fromHtml(name)
            binding.tvRVGame3.isEnabled = boolean
        }
    }

    init {
        list = ArrayList()
        type = viewType
    }
}