package com.selflearning.chemistree.f_mendeleev_table.table_view.render

import android.graphics.*
import androidx.annotation.ColorInt
import androidx.core.graphics.times
import com.selflearning.chemistree.f_mendeleev_table.table_view.TableView
import com.selflearning.chemistree.f_mendeleev_table.table_view.data.DataUi

class RenderAxis(
    private val tableView: TableView
) : Render(tableView.context) {


    private val viewPortHandler = tableView.viewPortHandler
    private val transformations = tableView.transformations

    private val cellHeight = tableView.cellHeight
    private val cellWidth = tableView.cellWidth


    private val rectRow = RectF(
        0f,
        0f,
        tableView.width.toFloat(),
        tableView.cellHeight / 2f
    )
    private val rectPeriod = RectF(
        0f,
        0f,
        tableView.cellWidth / 2f,
        tableView.height.toFloat()
    )

    override fun draw(canvas: Canvas, data: List<List<DataUi?>>) = with(canvas) {
        drawRows(data[0].size)
        drawPeriods(data.size)
    }

    //Vertical
    private fun Canvas.drawRows(countX: Int) {
        val nameY = axisLabelsPaint.getTextBaselineByCenter(cellHeight / 4f)
        axisBackgroundPaint.shader = shader(y1 = rectRow.bottom)
        rectRow.right = rectRow.right * transformations.scale
        drawRect(rectRow, axisBackgroundPaint)
        for (i in 1..countX) {
            val textWidth = getTextWidth(i.toString())
            val center = cellHeight * (i - 0.5f) * transformations.scale + cellHeight / 2
            val end = cellHeight * i + cellHeight / 2f + transformations.translationX
            val nameX =
                ((center - textWidth / 2) + transformations.translationX)
            if (nameX > cellHeight / 2) drawText(i.toString(), nameX, nameY, axisLabelsPaint)
        }
    }

    //Horizontal
    private fun Canvas.drawPeriods(countY: Int) {
        axisBackgroundPaint.shader = shader(x1 = rectPeriod.right)
        rectPeriod.bottom = rectPeriod.bottom * transformations.scale
        drawRect(rectPeriod, axisBackgroundPaint)
        for (i in 1..countY) {
            val textWidth = getTextWidth(i.toString())
            val center = cellWidth * (i - 0.5f) * transformations.scale + cellWidth / 2
            val nameX = (cellWidth / 4 - textWidth / 2)
            val nameY =
                (axisLabelsPaint.getTextBaselineByCenter(center) + transformations.translationY)
            if (nameY > cellWidth / 2) drawText(i.toString(), nameX, nameY, axisLabelsPaint)
        }
    }

    private fun shader(
        x0: Float = 0f,
        y0: Float = 0f,
        x1: Float = 0f,
        y1: Float = 0f,
        @ColorInt colors: IntArray = intArrayOf(Color.WHITE, Color.WHITE, Color.TRANSPARENT),
        positions: FloatArray = floatArrayOf(0f, 0.7f, 1f),
        tile: Shader.TileMode = Shader.TileMode.CLAMP
    ) = LinearGradient(
        x0,
        y0,
        x1,
        y1,
        colors,
        positions,
        tile
    )
}