package com.selflearning.chemistree.f_mendeleev_table.table_view.render

import android.content.Context
import android.graphics.Canvas
import com.selflearning.chemistree.f_mendeleev_table.table_view.render.Render

abstract class RenderTable(
    val context: Context
) : Render(context) {


    abstract fun drawBackground(canvas: Canvas)

}