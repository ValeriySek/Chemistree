package com.selflearning.chemistree.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.chemistry.elements.Element;
import com.selflearning.chemistree.chemistry.elements.ElementRepository;

public class ElementInfoActivity extends AppCompatActivity {

    private ElementRepository repository;

    private TextView tvSymbol;
    private TextView tvTitle;
    private TextView tvWeight;
    private TextView tvGroup;
    private TextView tvSubgroup;
    private TextView tvPeriod;
    private TextView tvBlock;
    private TextView tvElementCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_info);

        tvSymbol = findViewById(R.id.tvSymbol);
        tvTitle = findViewById(R.id.tvTitle);
        tvWeight = findViewById(R.id.tvWeight);
        tvGroup = findViewById(R.id.tvGroup);
        tvSubgroup = findViewById(R.id.tvSubgroup);
        tvPeriod = findViewById(R.id.tvPeriod);
        tvBlock = findViewById(R.id.tvBlock);
        tvElementCategory = findViewById(R.id.tvElementCategory);

        repository = new ElementRepository(getApplication());

        int elementNum = -1;

        Intent intent = getIntent();
        if(intent != null){
            elementNum = intent.getIntExtra("elementNum", -1);
        }

        Element element = repository.getElement(elementNum);
        if(element != null) {

            tvSymbol.setText(element.getSymbol());
            tvTitle.setText(element.getTitle());
            tvWeight.setText(String.valueOf(element.getWeight()));
            tvGroup.setText(String.valueOf(element.getGroup()));
            tvSubgroup.setText(element.getSubgroup());
            tvPeriod.setText(String.valueOf(element.getPeriod()));
            tvBlock.setText(element.getBlock());
            tvElementCategory.setText(element.getElementCategory());
        }
    }
}
