package com.selflearning.chemistree.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.selflearning.chemistree.chemistry.elements.ElementRepository
import com.selflearning.chemistree.databinding.ActivityElementInfoBinding

class ElementInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityElementInfoBinding

    private lateinit var repository: ElementRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_element_info)

        binding = ActivityElementInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = ElementRepository(application)
        var elementNum = -1
        val intent = intent
        if (intent != null) {
            elementNum = intent.getIntExtra("elementNum", -1)
        }
//        val element = repository.getElement(elementNum)
//        if (element != null) {
//            binding.tvSymbol.text = element.symbol
//            binding.tvTitle.text = element.title
//            binding.tvWeight.text = element.weight.toString()
//            binding.tvGroup.text = element.group.toString()
//            binding.tvSubgroup.text = element.subgroup
//            binding.tvPeriod.text = element.period.toString()
//            binding.tvBlock.text = element.block
//            binding.tvElementCategory.text = element.elementCategory
//        }
    }
}