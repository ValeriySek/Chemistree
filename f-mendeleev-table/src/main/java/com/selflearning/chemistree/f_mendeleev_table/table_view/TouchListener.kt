package com.selflearning.chemistree.f_mendeleev_table.table_view

import android.annotation.SuppressLint
import android.graphics.PointF
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Toast
import selflearning.chemistree.domain.chemistry.elements.Element
import kotlin.math.*

class TouchListener(
    val tableView: TableView
) : GestureDetector.SimpleOnGestureListener(),
    View.OnTouchListener {

    private val viewPortHandler = tableView.viewPortHandler
    private val transformations = tableView.transformations

    // Значения последнего эвента
    private val lastPoint = PointF()
    private var lastPointerId = 0

    private var _velocityTracker: VelocityTracker? = null
    private val decelerationVelocity = PointF()
    private val decelerationCurrentPoint = PointF()

    var scaling = 1f
    var scaleFactor = 1f
    var lastScale = 1f

    private val gestureDetector = GestureDetector(tableView.context, this)

    val scaleGestureDetector = ScaleGestureDetector(
        tableView.context,
        object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

            override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
                return super.onScaleBegin(detector)
            }

            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor
                scaleFactor = max(0.1f, min(detector.scaleFactor, 10f))
                val scal = scaling * scaleFactor
                scaling = if (scal in 0.5f..2f) scal else scaling
                val dx = (scaling - lastScale) * (detector.focusX - transformations.translationX)
                val dy = (scaling - lastScale) * (detector.focusY - transformations.translationY)
                transformations.setScale(scaling, dx, dy)
                Log.i("TAGGGGG", "detector.focusX scaling $scaling transformations.translationX ${transformations.translationX}")
                lastScale = scaling
                return true
            }
        })

    private var decelerationLastTime = 0L

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent): Boolean {

        gestureDetector.onTouchEvent(event)

        if (_velocityTracker == null) {
            _velocityTracker = VelocityTracker.obtain()
        }
        _velocityTracker?.addMovement(event)
        if (event.actionMasked == MotionEvent.ACTION_CANCEL) {
            if (_velocityTracker != null) {
                _velocityTracker?.recycle()
                _velocityTracker = null
            }
        }

        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                cancelDeceleration()
                lastPoint.set(event.x, event.y)
                lastPointerId = event.getPointerId(0)
                true
            }

            MotionEvent.ACTION_MOVE -> {
                // Если размер контента меньше размера View - сдвиг недоступен
                if (viewPortHandler.width < (tableView.contentWidth(transformations.scale))
                    || viewPortHandler.height < (tableView.contentHeight(transformations.scale))
                ) {
                    val pointerId = event.getPointerId(0)
                    // Чтобы избежать скачков - сдвигаем, только если поинтер(палец) тот же, что и раньше
                    if (lastPointerId == pointerId) {
                        transformations.addTranslation(event.x - lastPoint.x, event.y - lastPoint.y)
                    }

                    // Запоминаем поинтер и последнюю точку в любом случае
                    lastPoint.set(event.x, event.y)
                    lastPointerId = event.getPointerId(0)

                    true
                } else {
                    false
                }
            }

            MotionEvent.ACTION_CANCEL -> {

                if (!transformations.canScrollX) {

                }
                true
            }

            MotionEvent.ACTION_UP -> {

                val velocityTracker = _velocityTracker!!
                velocityTracker.computeCurrentVelocity(1000, 8000f)
                val velocityX = velocityTracker.getXVelocity(lastPointerId)
                val velocityY = velocityTracker.getYVelocity(lastPointerId)

                if (abs(velocityX) > 100 || abs(velocityY) > 100) {
                    cancelDeceleration()

                    decelerationLastTime = AnimationUtils.currentAnimationTimeMillis()

                    decelerationVelocity.x = velocityX
                    decelerationVelocity.y = velocityY

                    tableView.postInvalidateOnAnimation()
                }
                true
            }

            else -> false
        }
    }

    private fun getDistance(event: MotionEvent): Float {
        val dx = event.getX(0) - event.getX(1)
        val dy = event.getY(0) - event.getY(1)
        return hypot(dx, dy)
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.i("TAGG", "onSingleTapUp")
        return super.onSingleTapUp(e)
    }

    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        tableView.dataUi.forEach { list ->
            list.forEach {
                if (it != null && it.rect.contains(e.x, e.y)) {
                    val d = it.data as? Element
                    Toast.makeText(tableView.context, d?.symbol, Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onSingleTapConfirmed(e)
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        Log.i("TAGG", "onDoubleTap")
        return super.onDoubleTap(e)
    }


    private fun cancelDeceleration() {
        decelerationVelocity.x = 0f
        decelerationVelocity.y = 0f
    }

    fun computeScroll() {
        if (decelerationVelocity.x == 0f && decelerationVelocity.y == 0f) return

        val currentTime = AnimationUtils.currentAnimationTimeMillis()

        decelerationVelocity.x *= 0.9f
        decelerationVelocity.y *= 0.9f

        val timeInterval = (currentTime - decelerationLastTime).toFloat() / 1000f

        val distanceX: Float = decelerationVelocity.x * timeInterval
        val distanceY: Float = decelerationVelocity.y * timeInterval

        transformations.addTranslation(distanceX, distanceY)

        decelerationLastTime = currentTime

        if (abs(decelerationVelocity.x) >= 0.01 || abs(decelerationVelocity.y) >= 0.01)
            tableView.postInvalidateOnAnimation() // This causes computeScroll to fire, recommended for this by Google
        else {
            tableView.postInvalidate()
            cancelDeceleration()
        }
    }
}