package com.selflearning.chemistree.f_mendeleev_table.table_view

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Matrix
import android.util.Log

class Transforms(
    val tableView: TableView
) {

    val viewPortHandler = tableView.viewPortHandler
    private val matrix = Matrix()

    var percents = 0f
        private set

    var scale = 1f
        private set

    var translationX = 0f
        private set
    var translationY = 0f
        private set

    val minTranslationX: Float
        get() = (viewPortHandler.width - tableView.contentWidth(scale))
            .coerceAtMost(0f)

    val minTranslationY: Float
        get() = (viewPortHandler.height - tableView.contentHeight(scale))
            .coerceAtMost(0f)
    val canScrollX: Boolean
        get() = translationX < 0f && translationX > minTranslationX
    val canScrollY: Boolean
        get() = translationY < 0f && translationY > minTranslationY

    fun addTranslation(dx: Float, dy: Float) {
        translationX = (translationX + dx).coerceIn(minTranslationX, 0f)
        translationY = (translationY + dy).coerceIn(minTranslationY, 0f)
        Log.i("TAGGF", "translationX $translationX translationY $translationY")
        Log.i("TAGGGGGGG", "addTranslation dx $dx translationX $translationX")
        val x = if (canScrollX) dx else 0f
        val y = if (canScrollY) dy else 0f
//        updateTasks(x, y)
        updateTasks()
    }



    fun setPercents(percents: Float) {
        this.percents = percents
    }

    fun setScale(scale: Float, dx: Float, dy: Float) {
        this.scale = scale
        updateDataRects(scale, percents)
        translationX = (translationX + dx).coerceIn(minTranslationX, 0f)
        translationY = (translationY + dy).coerceIn(minTranslationY, 0f)
        Log.i("TAGGGGGGG", "setScale dx $dx translationX $translationX")
        updateTasks()
    }

    fun recalculate() {
        updateTasks()
    }

    private fun updateTasks() {
        // Подготовка матрицы для трансформации фигур тасок
        with(matrix) {
            reset()
            // Порядок имеет значение
            postTranslate(translationX, translationY)
        }
        tableView.dataUi.forEach {
            it.forEach { dataUi ->
                dataUi ?: return@forEach
                dataUi.transform(matrix)
            }
        }
        tableView.invalidate()
    }


    fun createAnimator(): Animator {
        val animator = ValueAnimator
            .ofFloat(0f, 1f).apply {
                duration = 8000
                addUpdateListener { va ->
                    val f = va.animatedValue as Float
                    updateDataRects(scale = scale, percents = f)
                    setPercents(f)
                }
            }
        return animator
    }

    fun updateDataRects(scale: Float = 1f, percents: Float = 0f) {
        tableView.dataUi.forEachIndexed { indexFirst, list ->
            list.forEachIndexed { indexSecond, dataUi ->
                dataUi ?: return@forEachIndexed
                dataUi.invalidateRect(Pair(indexFirst, indexSecond), scale, percents)
            }
        }
        recalculate()
    }
}