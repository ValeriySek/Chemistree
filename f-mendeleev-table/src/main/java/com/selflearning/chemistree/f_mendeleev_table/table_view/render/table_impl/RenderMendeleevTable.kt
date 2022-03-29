package com.selflearning.chemistree.f_mendeleev_table.table_view.render.table_impl

import android.animation.ValueAnimator
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.graphics.times
import com.selflearning.chemistree.f_mendeleev_table.table_view.TableView
import com.selflearning.chemistree.f_mendeleev_table.table_view.data.DataUi
import com.selflearning.chemistree.f_mendeleev_table.table_view.render.RenderTable
import com.selflearning.chemistree.f_mendeleev_table.R
import com.selflearning.chemistree.f_mendeleev_table.table_view.data.LongTableElementsList.maxWeight
import selflearning.chemistree.domain.chemistry.elements.Element

class RenderMendeleevTable(
    val tableView: TableView
) : RenderTable(tableView.context) {

    private val viewPortHandler = tableView.viewPortHandler
    private val transformations = tableView.transformations

    private val countX = 10
    private val countY = 20

    private val textRect = Rect()

    // Ширина столбца с периодом
    private val periodWidth = context.resources.getDimensionPixelSize(R.dimen.gant_row_height)

    // Высота строки
    private val rowHeight = context.resources.getDimensionPixelSize(R.dimen.gant_row_height)

    //  отступ
    private val cardMargin = context.resources.getDimension(R.dimen.gant_task_vertical_margin)
    private val minMargin = context.resources.getDimension(R.dimen.gant_task_text_min_margin)

    private val cornerRadius = context.resources.getDimension(R.dimen.gant_task_corner_radius)

    override fun drawBackground(canvas: Canvas) = with(canvas) {
        drawRect(0f, 0f, viewPortHandler.width, viewPortHandler.height, tableBackgroundPaint)
    }


    override fun draw(canvas: Canvas, data: List<List<DataUi?>>) {
        data.forEach {
            it.forEach { dataUi ->
                dataUi ?: return@forEach
                if (dataUi.isRectOnScreen) {
                    drawCard(canvas, dataUi)
                }
            }
        }
    }

    private fun drawCard(canvas: Canvas, dataUi: DataUi) = with(canvas) {

        val element = dataUi.data as? Element ?: return@with

        setItemColor(element.elementCategory)
        drawBackgroundAndStroke(dataUi)
        drawData(dataUi, element)
        drawLeftTopRoundRect(dataUi, "${element.atomicNumber}")
        drawLabels(dataUi, element)
    }

    private fun Canvas.drawData(dataUi: DataUi, element: Element) = with(this) {
//        val rect = RectF(dataUi.rect)
        val top = dataUi.rect.bottom - (dataUi.rect.height() * element.weight / maxWeight).toFloat()
//        ValueAnimator
//            .ofFloat(dataUi.rect.bottom, top).apply {
//                duration = 300
//                repeatCount = 0
//                addUpdateListener {
//                    dataUi.tempRect.top = it.animatedValue as Float
//                    if (element.atomicNumber == 56) Log.i("TAFF", dataUi.tempRect.toString())
//                    tableView.invalidate()
//                }
//                start()
//            }
        drawRect(
            dataUi.tempRect,
            paintSecondBackground
        )
    }

    private fun setItemColor(elementCategory: String) {
        defaultItemColor = itemColors[elementCategory] ?: "444444"
        Log.i("TAGGF", "itemColor $defaultItemColor")
        paintStrokeBackground.color = Color.parseColor("#${defaultItemColor}")
        paintSecondBackground.color = Color.parseColor("#${defaultItemColor}")
        paintSecondBackgroundAlpha.color = Color.parseColor("#0D${defaultItemColor}")
    }

    private fun Canvas.drawBackgroundAndStroke(dataUi: DataUi) = with(this) {
//        drawRoundBackgroundAndStroke(dataUi)
        drawBackgroundNoStroke(dataUi)
//        paintShadeBackground.shader = RadialGradient(
//            dataUi.rect.left,
//            dataUi.rect.top,
//            dataUi.rect.width() / 2,
//            color,
//            Color.TRANSPARENT,
//            Shader.TileMode.CLAMP
//        )
//        drawArc(
//            dataUi.rect.left - dataUi.rect.width() / 2,
//            dataUi.rect.top - dataUi.rect.width() / 2,
//            dataUi.rect.right - dataUi.rect.width() / 2,
//            dataUi.rect.bottom - dataUi.rect.width() / 2,
//            0f, 90f, true, paintShadeBackground
//        )
    }

    private fun Canvas.drawBackgroundNoStroke(dataUi: DataUi) = with(this) {
        cardBackgroundPaint.color = cardBackgroundPaint.color
        drawRect(
            dataUi.rect,
            paintSecondBackgroundAlpha
        )
    }

    private fun Canvas.drawRoundBackgroundAndStroke(dataUi: DataUi) = with(this) {
        drawRoundRect(
            dataUi.rect,
            cornerRadius,
            cornerRadius,
            cardBackgroundPaint
        )
        drawRoundRect(
            dataUi.rect,
            cornerRadius,
            cornerRadius,
            paintStrokeBackground
        )
    }

    private fun Canvas.drawLabels(dataUi: DataUi, element: Element) = with(this) {
        axisLabelsPaint.getTextBounds(element.symbol, 0, element.symbol.length, textRect)
        val tX = (dataUi.rect.left + (dataUi.rect.width()) / 2) - textRect.width() / 2
        val tY = (dataUi.rect.top + (dataUi.rect.height()) / 2)
        drawText(
            element.symbol,
            tX,
            axisLabelsPaint.getTextBaselineByCenter(tY),
            axisLabelsPaint
        )
    }

    private fun Canvas.drawLeftTopRoundRect(dataUi: DataUi, atomicNumber: String) = with(this) {
        smallLabelsPaint.getTextBounds(atomicNumber, 0, atomicNumber.length, textRect)
        val leftTopRect = RectF(
            dataUi.rect.left,
            dataUi.rect.top,
            dataUi.rect.left + cornerRadius + textRect.width(),
            dataUi.rect.top + minMargin * 2 + smallLabelsPaint.textHeight()
        )
        drawRect(leftTopRect, paintSecondBackground)
//        drawPath(
//            getRoundRectPath(
//                getCorners(topLeft = true, bottomRight = true),
//                leftTopRect
//            ),
//            paintSecondBackground
//        )
        val ttY = (leftTopRect.top + (leftTopRect.bottom - leftTopRect.top) / 2)
        val ttX = (leftTopRect.left + leftTopRect.width() / 2)
        drawText(
            atomicNumber,
            ttX,
            smallLabelsPaint.getTextBaselineByCenter(ttY),
            smallLabelsPaint
        )
    }

    private fun getCorners(
        topLeft: Boolean = false, topRight: Boolean = false,
        bottomRight: Boolean = false, bottomLeft: Boolean = false
    ): FloatArray {
        val radii = FloatArray(8)
        if (topLeft) {
            radii[0] = cornerRadius
            radii[1] = cornerRadius
        }
        if (topRight) {
            radii[2] = cornerRadius
            radii[3] = cornerRadius
        }
        if (bottomRight) {
            radii[4] = cornerRadius
            radii[5] = cornerRadius
        }
        if (bottomLeft) {
            radii[6] = cornerRadius
            radii[7] = cornerRadius
        }
        return radii
    }

    private fun getRoundRectPath(radii: FloatArray, rect: RectF): Path {
        val path = Path()
        path.addRoundRect(rect, radii, Path.Direction.CW)
        return path
    }
}