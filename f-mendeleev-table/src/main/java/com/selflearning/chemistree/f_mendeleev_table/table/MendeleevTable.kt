package com.selflearning.chemistree.f_mendeleev_table.table

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.selflearning.chemistree.f_mendeleev_table.R
import com.selflearning.chemistree.f_mendeleev_table.table_view.TableView
import com.selflearning.chemistree.f_mendeleev_table.table_view.data.LongTableElementsList
import com.selflearning.chemistree.f_mendeleev_table.table_view.render.table_impl.RenderMendeleevTable

class MendeleevTable : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mendeleev_table)

        val table = findViewById<TableView>(R.id.table)
        table.setTableRender(RenderMendeleevTable(table))
//
        table.setData(LongTableElementsList.elements)
//        table.animates()
//        if (savedInstanceState == null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.tableContainer, MTFragment.newInstance())
//                    .commit();
//        }
    }
}