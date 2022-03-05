package com.selflearning.chemistree.f_mendeleev_table.table_view.data

import android.graphics.Matrix
import android.graphics.RectF
import android.util.Log
import androidx.core.graphics.times
import com.selflearning.chemistree.f_mendeleev_table.table_view.TableView
import selflearning.chemistree.domain.chemistry.elements.Data

class DataUi(
    val data: Data?,
    private val tableView: TableView
) {


    private val viewPortHandler = tableView.viewPortHandler
    private val transformations = tableView.transformations
    private val width = tableView.cellWidth
    private val height = tableView.cellHeight
    val cardMargin = tableView.cardMargin
    var rect = RectF()
    // Начальный Rect для текущих размеров View
    private val untransformedRect = RectF()

    val isRectOnScreen: Boolean
        get() {

            val v = rect.left < viewPortHandler.width &&
                rect.right > 0 &&
                rect.top < viewPortHandler.height &&
                rect.bottom > 0
            Log.i("TAGGGG", "v $v")
            return v
        }

    fun invalidateRect(pair: Pair<Int, Int>, scale: Float) {
        untransformedRect.set(
            width / 2 + cardMargin + (pair.second * width * scale),
            width / 2 + cardMargin + (pair.first * height * scale),
            width / 2 - cardMargin + ((pair.second + 1) * width * scale),
            width / 2 - cardMargin + ((pair.first + 1) * height * scale)
        )
        rect.set(untransformedRect)
    }

    fun transform(matrix: Matrix) {
        matrix.mapRect(rect, untransformedRect)
    }
}