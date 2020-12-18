package com.selflearning.chemistree.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.selflearning.chemistree.R
import com.selflearning.chemistree.chemistry.elements.Element
import com.selflearning.chemistree.databinding.ItemEmptyBinding
import com.selflearning.chemistree.databinding.ItemEmptyHalfHeightBinding
import com.selflearning.chemistree.databinding.ItemTableElementBinding
import com.selflearning.chemistree.utilities.extentions.doAsync

class MendAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListOf<Element?>()

    fun ittems(element: Element){
        items.add(element)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            items[position] != null -> {
                1
            }
            position < (items.size / 2) -> {
                2
            }
            else -> {
                3
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            1 -> {
                val binding = ItemTableElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MendViewHolder(binding, parent.context)
            }
            2 -> {
                val binding = ItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EmptyViewHolder(binding)
            }
            else -> {
                val binding = ItemEmptyHalfHeightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EmptyHalfViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position]?.let {
            if(holder is MendViewHolder) holder.bind(it)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MendViewHolder(val binding: ItemTableElementBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {

        var back: Drawable? = null
        var backNum: Drawable? = null

        fun bind(element: Element) {
            Log.i("TAGG", "${element.atomicNumber}")
            binding.elementNumber.text = element.atomicNumber.toString()
            binding.elementSymbol.text = element.symbol
            binding.elementName.text = element.title

            binding.root.doAsync({
                return@doAsync setBack(element.elementCategory)
            }, {
                binding.elementContainer.background = back
                binding.elementNumber.background = backNum
            })



        }

        private fun setBack(category: String){
            when(category){
                "reactiveNonmetal" -> {
                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_blue)
                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_blue)
                }
                "nobleGas" -> {
                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_sandy_brown)
                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_sandy_brown)
                }
                "alkaliMetal" -> {
                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_olive)
                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_olive)
                }
                "alkalineEarthMetal" -> {
                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_green)
                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_green)
                }
                "metalloid" -> {
                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_blue_violet)
                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_blue_violet)
                }
                "postTransitionMetal" -> {
                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_antique_fuchsia)
                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_antique_fuchsia)
                }
                "transitionMetal" -> {
                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_celadon_blue)
                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_celadon_blue)
                }
                "lanthanide" -> {
                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_brown)
                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_brown_400)
                }
                "actinide" -> {
                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_brown_600)
                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_brown_600)
                }
                else -> return
            }
        }
    }
    class EmptyViewHolder(val binding: ItemEmptyBinding) : RecyclerView.ViewHolder(binding.root)
    class EmptyHalfViewHolder(val binding: ItemEmptyHalfHeightBinding) : RecyclerView.ViewHolder(binding.root)
}