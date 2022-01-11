package com.selflearning.chemistree.f_mendeleev_table.table_view.data

import android.graphics.Matrix
import android.graphics.RectF
import com.selflearning.chemistree.f_mendeleev_table.table_view.TableView
import selflearning.chemistree.domain.chemistry.elements.Data

class DataUi(
    val data: Data?,
    private val tableView: TableView,
//    private val pair: Pair<Int, Int>
) {


    private val viewPortHandler = tableView.viewPortHandler
    private val transformations = tableView.transformations
    private val width = tableView.cellWidth
    private val height = tableView.cellHeight
    val cardMargin = tableView.cardMargin
    val rect = RectF()
    // Начальный Rect для текущих размеров View
    private val untransformedRect = RectF()

    val isRectOnScreen: Boolean
        get() = rect.left < viewPortHandler.width &&
                rect.right > 0 &&
                rect.top < viewPortHandler.height &&
                rect.bottom > 0

    fun invalidateRect(pair: Pair<Int, Int>) {
        untransformedRect.set(
            width / 2 + cardMargin + (pair.second * width).toFloat(),
            height / 2 + cardMargin + (pair.first * height).toFloat(),
            width / 2 - cardMargin + ((pair.second + 1) * width).toFloat(),
            height / 2 - cardMargin + ((pair.first + 1) * height).toFloat()
        )
        rect.set(untransformedRect)
    }

    fun transform(matrix: Matrix) {
        matrix.mapRect(rect, untransformedRect)
    }
}