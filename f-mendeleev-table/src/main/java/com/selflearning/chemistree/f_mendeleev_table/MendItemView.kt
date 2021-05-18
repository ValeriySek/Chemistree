package com.selflearning.chemistree.f_mendeleev_table

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorInt


/**
 * TODO: document your custom view class.
 */
class MendItemView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var viewHeight: Float = 0f
    private var viewWidth: Float = 0f

    private var textWidth: Float = 0f
    private var textHeight: Float = 0f

    private var cornerRadius: Float = 0f
    private var strokeWidth: Float = 0f

    private var centerTextSize: Float = 0f
    private var topLeftTextSize: Float = 0f
    private var bottomCenterTextSize: Float = 0f

    private val textRect = Rect()
    private var topLeftRect = RectF()
    private var topLeftRectHeight = 0f

    @ColorInt
    var background: Int = DEFAULT_BACKGROUND_COLOR
        set(@ColorInt value) {
            field = value
            paintBackground.color = background
            invalidate()
        }

    var strokeBackground: Int = DEFAULT_STROKE_COLOR
        set(@ColorInt value) {
            field = value
            paintStrokeBackground.color = strokeBackground
            paintSecondBackground.color = strokeBackground
            invalidate()
        }


    var topLeftText: String? = TOP_LEFT_TEXT
        set(value) {
            field = value
            invalidateTextPaintAndMeasurements()
        }

    var centerText: String? = CENTER_TEXT
        set(value) {
            field = value
            invalidateTextPaintAndMeasurements()
        }

    var bottomCenterText: String? = BOTTOM_CENTER_TEXT
        set(value) {
            field = value
            invalidateTextPaintAndMeasurements()
        }


    private val paintBackground: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = background
    }

    private val paintSecondBackground: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.DKGRAY
    }

    private val paintStrokeBackground: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = Color.DKGRAY
    }

    private var textPaint: Paint = TextPaint().apply {
        flags = Paint.ANTI_ALIAS_FLAG
        textAlign = Paint.Align.CENTER
        color = Color.WHITE
    }

    init {
        init(attrs, defStyleAttr)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val typedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.MendItemView,
                defStyle,
                0
        )
        try {
//            topLeftText = typedArray.getString(
//                    R.styleable.MendItemView_topLeftText
//            ).toString()
//            _exampleColor = typedArray.getColor(
//                    R.styleable.MendItemView_exampleColor,
//                    exampleColor)
            // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
            // values that should fall on pixel boundaries.
            topLeftTextSize = typedArray.getDimension(
                    R.styleable.MendItemView_exampleDimension,
                    topLeftTextSize)


            // Set up a default TextPaint object
            textPaint

        } catch (e: Exception) {

        } finally {
            typedArray.recycle()
            invalidateTextPaintAndMeasurements()
        }

        // Update TextPaint and text measurements from attributes
//        invalidateTextPaintAndMeasurements()
    }

    private fun invalidateTextPaintAndMeasurements() {
        textPaint.let {
            it.color = Color.WHITE
//            topLeftTextHeight = it.fontMetrics.descent - it.fontMetrics.ascent ?: 0f
//            topLeftTextWidth = it.measureText(topLeftText) ?: 0f
            it.getTextBounds(topLeftText, 0, topLeftText!!.length, textRect)
            textWidth = textRect.width().toFloat()
            textHeight = textRect.height().toFloat()
        }
    }

    private fun invalidateTextSize(size: Float) {
        textPaint.apply {
            textSize = size
        }
        invalidateTextPaintAndMeasurements()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        MeasureSpec.getSize(widthMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val density = context.resources.displayMetrics.scaledDensity
        viewHeight = 60f * density
        viewWidth = 60f * density
        centerTextSize = viewHeight / 2
        topLeftTextSize = viewHeight / 6
        bottomCenterTextSize = viewHeight / 4
        cornerRadius = viewHeight / 6
        topLeftRectHeight = viewHeight / 5
        strokeWidth = viewHeight / 50
        paintStrokeBackground.strokeWidth = strokeWidth
//        topLeftRect.set(0f, 0f, topLeftTextSize + textWidth, topLeftTextSize)
        Log.i("TAGG", "$width  $height")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawRoundRect(
                strokeWidth / 2, strokeWidth / 2,
                viewWidth - strokeWidth / 2,
                viewHeight - strokeWidth / 2,
                cornerRadius,
                cornerRadius,
                paintBackground
        )
        canvas.drawRoundRect(
                strokeWidth / 2, strokeWidth / 2,
                viewWidth - strokeWidth / 2,
                viewHeight - strokeWidth / 2,
                cornerRadius,
                cornerRadius,
                paintStrokeBackground

        )
        invalidateTextSize(topLeftTextSize)
        topLeftRect.set(0f, 0f, topLeftRectHeight + textWidth, topLeftRectHeight)
        canvas.drawPath(
                getRoundRectPath(
                        getCorners(topLeft = true, bottomRight = true),
                        topLeftRect),
                paintSecondBackground
        )

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom

        val contentWidth = viewWidth - paddingLeft - paddingRight
        val contentHeight = viewHeight - paddingTop - paddingBottom

        topLeftText?.let {
            canvas.drawText(it,
                    paddingLeft.toFloat() + topLeftRect.width() / 2,
                    paddingTop.toFloat() + (topLeftRectHeight + textHeight) / 2,
                    textPaint)
        }

        invalidateTextSize(centerTextSize)
        centerText?.let {
            canvas.drawText(
                    it,
                    viewWidth / 2f,
                    (viewHeight + textHeight) / 2,
                    textPaint
            )
        }
        invalidateTextSize(bottomCenterTextSize)
        bottomCenterText?.let {
            canvas.drawText(
                    it,
                    viewWidth / 2f,
                    viewHeight - textHeight / 2,
                    textPaint
            )
        }
    }

    fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
    }

    fun spToPx(sp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics)
    }

    fun dpToSp(dp: Float): Float {
        return (dpToPx(dp) / context.resources.displayMetrics.scaledDensity)
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

    companion object {
        private const val TOP_LEFT_TEXT = "1000"
        private const val CENTER_TEXT = "H"
        private const val BOTTOM_CENTER_TEXT = "Hydrogen"
        private const val DEFAULT_TEXT_COLOR = Color.WHITE
        private val DEFAULT_BACKGROUND_COLOR = Color.rgb(44,44,48)
        private const val DEFAULT_STROKE_COLOR = Color.DKGRAY

    }
}
