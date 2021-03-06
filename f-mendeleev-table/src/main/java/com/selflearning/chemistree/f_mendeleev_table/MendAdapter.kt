package com.selflearning.chemistree.f_mendeleev_table

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.selflearning.chemistree.f_mendeleev_table.databinding.ItemEmptyBinding
import com.selflearning.chemistree.f_mendeleev_table.databinding.ItemEmptyHalfHeightBinding
import com.selflearning.chemistree.f_mendeleev_table.databinding.ItemNewBinding
import com.selflearning.chemistree.f_mendeleev_table.databinding.ItemTableElementBinding
import selflearning.chemistree.domain.chemistry.elements.Element

class MendAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListOf<Element?>()
    lateinit var mitem: View

    fun ittems(element: Element) {
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
//            1 -> {
//                mitem = MendItemView(parent.context)
//                mitem.layoutParams = ViewGroup.LayoutParams(100, 100)
//                mitem.layoutParams.height = 100
//                mitem.layoutParams.width = 100
//                parent.addView(item)
//                LayoutInflater.from(parent.context).inflate(item, parent, false)
//                MendViewHolder(mitem, parent.context)
//            }
            1 -> {
                val binding = ItemNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MendViewHolder(binding, parent.context)
            }
//           1 -> {
//                val binding = ItemTableElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//                MendViewHolder(binding, parent.context)
//            }
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
            if (holder is MendViewHolder) holder.bind(it)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

        class MendViewHolder(val binding: ItemNewBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
//    class MendViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {

        var back = 0
        var backNum: Drawable? = null

        //        fun bind(element: Element) {
//            Log.i("TAGG", "${element.atomicNumber}")
//            binding.elementNumber.text = element.atomicNumber.toString()
//            binding.elementSymbol.text = element.symbol
//            binding.elementName.text = element.title
//            binding.elementContainer.background = back
//            binding.elementNumber.background = backNum
//        }
//        fun bind(element: Element) {
//            Log.i("TAGG", "${element.atomicNumber}")
//            setBack(element.elementCategory)
//            binding.mendItem.strokeBackground = back
//            binding.mendItem.topLeftText = element.atomicNumber.toString()
//            binding.mendItem.centerText = element.symbol
//            binding.mendItem.bottomCenterText = element.title
//        }
        fun bind(element: Element) {
            Log.i("TAGG", "${element.atomicNumber}")
            setBack(element.elementCategory)
//        binding.mendItem.strokeBackground = back
//        binding.mendItem.topLeftText = element.atomicNumber.toString()
//        binding.mendItem.centerText = element.symbol
//        binding.mendItem.bottomCenterText = element.title
        }

        private fun setBack(category: String) {
            when (category) {
                "reactiveNonmetal" -> {
                    back = Color.rgb(42, 54, 119)
//                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_blue)
//                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_blue)
                }
                "nobleGas" -> {
                    back = Color.rgb(233, 143, 64)
//                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_sandy_brown)
//                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_sandy_brown)
                }
                "alkaliMetal" -> {
                    back = Color.rgb(128, 128, 0)
//                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_olive)
//                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_olive)
                }
                "alkalineEarthMetal" -> {
                    back = Color.rgb(31, 122, 39)
//                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_green)
//                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_green)
                }
                "metalloid" -> {
                    back = Color.rgb(112, 44, 179)
//                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_blue_violet)
//                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_blue_violet)
                }
                "postTransitionMetal" -> {
                    back = Color.rgb(145, 92, 131)
//                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_antique_fuchsia)
//                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_antique_fuchsia)
                }
                "transitionMetal" -> {
                    back = Color.rgb(0, 123, 167)
//                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_celadon_blue)
//                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_celadon_blue)
                }
                "lanthanide" -> {
                    back = Color.rgb(141, 110, 99)
//                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_brown)
//                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_brown_400)
                }
                "actinide" -> {
                    back = Color.rgb(109, 76, 64)
//                    back = ContextCompat.getDrawable(context, R.drawable.mendeleev_background_brown_600)
//                    backNum = ContextCompat.getDrawable(context, R.drawable.round_background_brown_600)
                }
                else -> return
            }
        }
    }

    class EmptyViewHolder(val binding: ItemEmptyBinding) : RecyclerView.ViewHolder(binding.root)
    class EmptyHalfViewHolder(val binding: ItemEmptyHalfHeightBinding) : RecyclerView.ViewHolder(binding.root)
}