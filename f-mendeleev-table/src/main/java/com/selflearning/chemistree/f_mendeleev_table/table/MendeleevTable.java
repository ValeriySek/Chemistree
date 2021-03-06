package com.selflearning.chemistree.f_mendeleev_table.table;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.selflearning.chemistree.f_mendeleev_table.MTFragment;
import com.selflearning.chemistree.f_mendeleev_table.R;


public class MendeleevTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mendeleev_table);



        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.tableContainer, MTFragment.newInstance())
                    .commit();
        }
    }
}
