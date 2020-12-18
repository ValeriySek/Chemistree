package com.selflearning.chemistree.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selflearning.chemistree.adapter.ElementsAdapter.ElementsViewHolder
import com.selflearning.chemistree.chemistry.elements.Element
import com.selflearning.chemistree.databinding.ElementsListBinding

class ElementsAdapter : RecyclerView.Adapter<ElementsViewHolder>() {
    private lateinit var elementList: List<Element>
    fun setElementsList(elementList: List<Element>) {
        this.elementList = elementList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementsViewHolder {
        val binding = ElementsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ElementsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ElementsViewHolder, position: Int) {
        if (elementList.isNotEmpty()) {
            val element = elementList[position]
            holder.bind(element)
        }
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    inner class ElementsViewHolder(private val binding: ElementsListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(element: Element){
            binding.idOfElement.text = String.format("%s", element.atomicNumber)
            binding.symbolOfElement.text = element.symbol
            binding.ruTitleOfElement.text = element.title
            binding.weightOfElement.text = String.format("%s", element.weight)
        }
    }
}