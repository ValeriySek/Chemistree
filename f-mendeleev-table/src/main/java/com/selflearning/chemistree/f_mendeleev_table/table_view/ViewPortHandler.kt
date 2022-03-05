package com.selflearning.chemistree.f_mendeleev_table.table_view

import android.graphics.RectF
import android.util.Log


class ViewPortHandler {

    var width = 0f
    var height = 0f

    val contentRect = RectF()

    fun setTableDimens(width: Float, height: Float) {
        this.width = width
        this.height = height
    }

    fun setContentRect(
        offsetLeft: Float = 0f,
        offsetTop: Float = 0f,
        offsetRight: Float = 0f,
        offsetBottom: Float = 0f
    ) {
        contentRect[offsetLeft, offsetTop, width - offsetRight] =  (height - offsetBottom)
    }
}