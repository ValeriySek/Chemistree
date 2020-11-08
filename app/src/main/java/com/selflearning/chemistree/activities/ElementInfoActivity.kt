package com.selflearning.chemistree.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.selflearning.chemistree.R
import com.selflearning.chemistree.chemistry.elements.ElementRepository

class ElementInfoActivity : AppCompatActivity() {
    private lateinit var repository: ElementRepository
    private lateinit var tvSymbol: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvWeight: TextView
    private lateinit var tvGroup: TextView
    private lateinit var tvSubgroup: TextView
    private lateinit var tvPeriod: TextView
    private lateinit var tvBlock: TextView
    private lateinit var tvElementCategory: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_element_info)
        tvSymbol = findViewById(R.id.tvSymbol)
        tvTitle = findViewById(R.id.tvTitle)
        tvWeight = findViewById(R.id.tvWeight)
        tvGroup = findViewById(R.id.tvGroup)
        tvSubgroup = findViewById(R.id.tvSubgroup)
        tvPeriod = findViewById(R.id.tvPeriod)
        tvBlock = findViewById(R.id.tvBlock)
        tvElementCategory = findViewById(R.id.tvElementCategory)
        repository = ElementRepository(application)
        var elementNum = -1
        val intent = intent
        if (intent != null) {
            elementNum = intent.getIntExtra("elementNum", -1)
        }
        val element = repository.getElement(elementNum)
        if (element != null) {
            tvSymbol.text = element.symbol
            tvTitle.text = element.title
            tvWeight.text = element.weight.toString()
            tvGroup.text = element.group.toString()
            tvSubgroup.text = element.subgroup
            tvPeriod.text = element.period.toString()
            tvBlock.text = element.block
            tvElementCategory.text = element.elementCategory
        }
    }
}