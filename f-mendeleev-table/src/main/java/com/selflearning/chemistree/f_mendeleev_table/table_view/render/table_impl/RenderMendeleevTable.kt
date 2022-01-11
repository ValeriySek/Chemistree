package com.selflearning.chemistree.f_mendeleev_table.table_view.render.table_impl

import android.graphics.*
import com.selflearning.chemistree.f_mendeleev_table.table_view.TableView
import com.selflearning.chemistree.f_mendeleev_table.table_view.data.DataUi
import com.selflearning.chemistree.f_mendeleev_table.table_view.render.RenderTable
import com.selflearning.chemistree.f_mendeleev_table.R
import selflearning.chemistree.domain.chemistry.elements.Element

class RenderMendeleevTable(
    tableView: TableView
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

    override fun drawBackground(canvas: Canvas) = with(canvas){
        drawRect(0f, 0f, viewPortHandler.width, viewPortHandler.height, tableBackgroundPaint)
    }


    override fun draw(canvas: Canvas, data: List<List<DataUi?>>) {
        data.forEach {
            it.forEach {dataUi ->
                dataUi ?: return@forEach
                if (dataUi.isRectOnScreen) {
                    drawCard(canvas, dataUi)
                }
            }
        }
    }

    private fun drawCard(canvas: Canvas, dataUi: DataUi) = with(canvas) {

        val element = dataUi.data as Element?
        element ?: return@with

        val color = itemColors[element.elementCategory] ?: Color.DKGRAY
        paintStrokeBackground.color = color
        paintSecondBackground.color = color

        drawBackgroundAndStroke(canvas, dataUi, color)

        val atomicNumber = element.atomicNumber.toString()
        smallLabelsPaint.getTextBounds(atomicNumber, 0, atomicNumber.length, textRect)
        val atomicNumberWidth = textRect.width()

        val leftTopRect = RectF(
            dataUi.rect.left,
            dataUi.rect.top,
            dataUi.rect.left + cornerRadius + atomicNumberWidth,
            dataUi.rect.top + minMargin * 2 + smallLabelsPaint.textHeight()
        )
        val ttY = (leftTopRect.top + (leftTopRect.bottom - leftTopRect.top) / 2 )
        drawPath(
            getRoundRectPath(
                getCorners(topLeft = true, bottomRight = true),
                leftTopRect
            ),
            paintSecondBackground
        )
        val ttX = (leftTopRect.left + leftTopRect.width() / 2)
        drawText(
            atomicNumber,
            ttX,
            smallLabelsPaint.getTextBaselineByCenter(ttY),
            smallLabelsPaint
        )
        axisLabelsPaint.getTextBounds(element.symbol, 0, element.symbol.length, textRect)
        val tX = (dataUi.rect.left + (dataUi.rect.width()) / 2 ) - textRect.width() / 2
        val tY = (dataUi.rect.top + (dataUi.rect.height()) / 2 )
        drawText(
            element.symbol,
            tX,
            axisLabelsPaint.getTextBaselineByCenter(tY),
            axisLabelsPaint
        )
    }

    fun drawBackgroundAndStroke(canvas: Canvas, dataUi: DataUi, color: Int) = with(canvas) {
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

    private fun getCorners(topLeft: Boolean = false, topRight: Boolean = false,
                           bottomRight: Boolean = false, bottomLeft: Boolean = false): FloatArray {
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