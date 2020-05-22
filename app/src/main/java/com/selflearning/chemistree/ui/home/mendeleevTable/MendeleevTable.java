package com.selflearning.chemistree.ui.home.mendeleevTable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.selflearning.chemistree.R;
import com.selflearning.chemistree.ui.home.mendeleevTable.LongTable.LongTable;

public class MendeleevTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mendeleev_table);



        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.tableContainer, LongTable.newInstance())
                    .commit();
        }
    }
}
