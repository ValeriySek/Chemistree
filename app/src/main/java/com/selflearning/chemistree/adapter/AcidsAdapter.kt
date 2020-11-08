package com.selflearning.chemistree.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selflearning.chemistree.adapter.AcidsAdapter.AcidsViewHolder
import com.selflearning.chemistree.chemistry.inorganic.bases.Bases
import com.selflearning.chemistree.databinding.AcidsListBinding
import com.selflearning.chemistree.listeners.OnItemClickListener
import java.util.*

class AcidsAdapter : RecyclerView.Adapter<AcidsViewHolder>() {
    private var acidsList: List<Bases> = ArrayList()
    private lateinit var onItemClickListener: OnItemClickListener

    fun setDataList(acidsList: List<Bases>) {
        this.acidsList = acidsList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcidsViewHolder {
        val binding = AcidsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AcidsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AcidsViewHolder, position: Int) {
        if (acidsList.isNotEmpty()) {
            val acid = acidsList[position]
            holder.bind(acid)
        }
    }

    override fun getItemCount(): Int {
        return acidsList.size
    }

    inner class AcidsViewHolder(private val binding: AcidsListBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        override fun onClick(v: View) {
            onItemClickListener.onItemClick(adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(base: Bases) {
            binding.textViewAcidFormula.text = Html.fromHtml(base.formula.replace("([2-9]*)".toRegex(), "<sub><small>$1</small></sub>"))
            binding.textViewAcidRuName.text = base.name
        }
    }
}