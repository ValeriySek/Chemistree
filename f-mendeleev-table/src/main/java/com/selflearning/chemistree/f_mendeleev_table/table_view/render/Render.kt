package com.selflearning.chemistree.f_mendeleev_table.table_view.render

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import androidx.core.content.ContextCompat
import com.selflearning.chemistree.f_mendeleev_table.table_view.data.DataUi
import com.selflearning.chemistree.f_mendeleev_table.table_view.getDimension
import com.selflearning.chemistree.f_mendeleev_table.R

abstract class Render(
    context: Context
//    var viewPortHandler: ViewPortHandler
) {

    val itemColors = mapOf(
        "reactiveNonmetal" to Color.rgb(42, 54, 119),
        "nobleGas" to Color.rgb(233, 143, 64),
        "alkaliMetal" to Color.rgb(128, 128, 0),
        "alkalineEarthMetal" to Color.rgb(31, 122, 39),
        "metalloid" to Color.rgb(112, 44, 179),
        "postTransitionMetal" to Color.rgb(145, 92, 131),
        "transitionMetal" to Color.rgb(0, 123, 167),
        "lanthanide" to Color.rgb(141, 110, 99),
        "actinide" to Color.rgb(109, 76, 64),
    )

    val axisBackgroundPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    val tableBackgroundPaint = Paint().apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.grey_200)
    }

    val cardBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.grey_100)
    }

    val axisSeparatorPaint = Paint().apply {
        strokeWidth = context.getDimension(R.dimen.gant_separator_width)
        color = ContextCompat.getColor(context, R.color.grey_500)
    }

    val smallLabelsPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = context.getDimension(R.dimen.gant_task_small_text_size)
        color = ContextCompat.getColor(context, R.color.white)
        textAlign = Paint.Align.CENTER
    }

    val axisLabelsPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = context.getDimension(R.dimen.gant_task_name_text_size)
        color = ContextCompat.getColor(context, R.color.grey_700)
    }

    val paintStrokeBackground: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = Color.DKGRAY
        strokeWidth = 4f
    }

    val paintShadeBackground: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    val paintSecondBackground: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.DKGRAY
    }

    fun getTextWidth(text: String): Float {
        return axisLabelsPaint.measureText(text)
    }

    fun Paint.getTextBaselineByCenter(center: Float) = center - (descent() + ascent()) / 2
    fun Paint.textHeight() = descent() - ascent()

    abstract fun draw(canvas: Canvas, data: List<List<DataUi?>>)
}