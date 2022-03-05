package com.selflearning.chemistree.f_mendeleev_table.table_view


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.selflearning.chemistree.f_mendeleev_table.R
import selflearning.chemistree.domain.chemistry.elements.Data
import com.selflearning.chemistree.f_mendeleev_table.table_view.data.DataUi
import com.selflearning.chemistree.f_mendeleev_table.table_view.render.RenderAxis
import com.selflearning.chemistree.f_mendeleev_table.table_view.render.RenderTable

class TableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Ширина столбца с периодом
    var cellWidth = (resources.getDimensionPixelSize(R.dimen.gant_row_height))

    // Высота строки
    var cellHeight = (resources.getDimensionPixelSize(R.dimen.gant_row_height))

    //  отступ
    val cardMargin = resources.getDimension(R.dimen.gant_task_vertical_margin)


    fun contentWidth(scale: Float = 1f): Float = cellWidth * dataUi[0].size * scale + (cellWidth / 2) + cardMargin * 2

    fun contentHeight(scale: Float = 1f): Float = cellHeight * dataUi.size * scale + (cellWidth / 2) + cardMargin * 2

    private val rectRow = Rect()
    private val rectPeriod = Rect()

    val viewPortHandler = ViewPortHandler()

    // Отвечает за зум и сдвиги
    val transformations = Transforms(this)
    val touchListener = TouchListener(this)

//    // Обнаружение и расчет скейла
//    private val scaleGestureDetector = GestureDetector(context, ScaleListener())


    lateinit var renderTable: RenderTable
    lateinit var renderAxis: RenderAxis

    fun setTableRender(render: RenderTable) {
        renderTable = render
        invalidate()
    }

    private var data: List<List<Data?>> = emptyList()
    var dataUi: List<List<DataUi?>> = emptyList()

    fun setData(data: List<List<Data?>>) {
        if(this.data != data) {
            this.data = data
            dataUi = data.map { datas ->
                datas.map {
                    DataUi(it, this)
                }
            }
            updateDataRects()
            invalidate()
        }
    }

    fun updateDataRects(scale: Float = 1f) {
        dataUi.forEachIndexed { indexFirst, list ->
            list.forEachIndexed { indexSecond, dataUi ->
                dataUi ?: return@forEachIndexed
                dataUi.invalidateRect(Pair(indexFirst, indexSecond), scale)
            }
        }
        transformations.recalculate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            contentWidth()
        } else {
            // Даже если AT_MOST занимаем все доступное место, т.к. может быть зум
            MeasureSpec.getSize(widthMeasureSpec)
        }

        // Высота всех строк с тасками + строки с периодами
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        val height = when (MeasureSpec.getMode(heightMeasureSpec)) {
            // Нас никто не ограничивает - занимаем размер контента
            MeasureSpec.UNSPECIFIED -> contentHeight()
            // Ограничение "не больше, не меньше" - занимаем столько, сколько пришло в спеке
            MeasureSpec.EXACTLY -> heightSpecSize
            // Можно занять меньше места, чем пришло в спеке, но не больше
            MeasureSpec.AT_MOST -> heightSpecSize.coerceAtMost(heightSpecSize)
            // Успокаиваем компилятор, сюда не попадем
            else -> error("Unreachable")
        }

        setMeasuredDimension(width.toInt(), height.toInt())
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rectRow.set(cellHeight / 2, 0, w, cellHeight / 2)
        rectPeriod.set(0, 0, cellWidth / 2, h)
        viewPortHandler.setTableDimens(w.toFloat(), h.toFloat())
        viewPortHandler.setContentRect(offsetLeft = cellHeight / 2f, offsetTop = cellHeight / 2f)
        renderAxis  = RenderAxis(this)

        requestLayout()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) = with(canvas) {
        drawBackground()
        drawCards()
        renderAxis.draw(this, dataUi)
    }

    private fun Canvas.drawBackground() {
        renderTable.drawBackground(this)
    }

    private fun Canvas.drawCards() {
        renderTable.draw(this, dataUi)
    }

    override fun computeScroll() {
        touchListener.computeScroll()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        return if (event.pointerCount > 1) touchListener.scaleGestureDetector.onTouchEvent(event) else
            touchListener.onTouch(this, event)
    }
}