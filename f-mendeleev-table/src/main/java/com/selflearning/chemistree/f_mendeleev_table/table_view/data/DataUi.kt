package com.selflearning.chemistree.f_mendeleev_table.table_view.data

import android.graphics.Matrix
import android.graphics.RectF
import androidx.core.graphics.plus
import com.selflearning.chemistree.f_mendeleev_table.table_view.TableView
import selflearning.chemistree.domain.chemistry.elements.Data
import selflearning.chemistree.domain.chemistry.elements.Element

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

    fun updateTempRect() {
        untransformedTempRect.set(tempRect)
    }

    fun updateTempRect(f: Float) {
        val element = data as? Element ?: return
        val r = (element.weight / LongTableElementsList.maxWeight) * f
        tempRect.set(
            tempRect.left,
            (tempRect.bottom - rect.height() * r).toFloat(),
            tempRect.right,
            tempRect.bottom
        )
//        untransformedTempRect.set(tempRect)
    }
    var tempRect = RectF()

    // Начальный Rect для текущих размеров View
    private val untransformedRect = RectF()
    private val untransformedTempRect = RectF()

    val isRectOnScreen: Boolean
        get() {
            return rect.left < viewPortHandler.width &&
                    rect.right > 0 &&
                    rect.top < viewPortHandler.height &&
                    rect.bottom > 0
        }

    fun invalidateRect(pair: Pair<Int, Int>, scale: Float) {
        untransformedRect.set(
            width / 2 + cardMargin + (pair.second * width * scale),
            width / 2 + cardMargin + (pair.first * height * scale),
            width / 2 - cardMargin + ((pair.second + 1) * width * scale),
            width / 2 - cardMargin + ((pair.first + 1) * height * scale)
        )
        rect.set(untransformedRect)
        untransformedTempRect.set(
            untransformedRect.left,
            untransformedRect.bottom,
            untransformedRect.right,
            untransformedRect.bottom
        )
        tempRect.set(untransformedTempRect)
    }

    fun transform(matrix: Matrix) {
        matrix.mapRect(rect, untransformedRect)
        matrix.mapRect(tempRect, untransformedTempRect)
    }
}