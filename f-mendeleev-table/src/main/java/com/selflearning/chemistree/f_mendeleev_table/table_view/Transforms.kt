package com.selflearning.chemistree.f_mendeleev_table.table_view

import android.graphics.Matrix

class Transforms(
    val tableView: TableView
) {

    val viewPortHandler = tableView.viewPortHandler
    private val matrix = Matrix()

    var translationX = 0f
        private set
    var translationY = 0f
        private set

    val minTranslationX: Float
        get() = (viewPortHandler.width - tableView.contentWidth.toFloat())
            .coerceAtMost(0f)

    val minTranslationY: Float
        get() = (viewPortHandler.height - tableView.contentHeight.toFloat())
            .coerceAtMost(0f)
    val canScrollX: Boolean
        get() = translationX < 0f && translationX > minTranslationX
    val canScrollY: Boolean
        get() = translationY < 0f && translationY > minTranslationY

    fun addTranslation(dx: Float, dy: Float) {
        translationX = (translationX + if (canScrollX) dx else dx / 4)
                .coerceIn(minTranslationX, 0f)
        translationY = (translationY + if (canScrollY) dy else dy / 4)
                .coerceIn(minTranslationY, 0f)
        tableView.invalidate()
        updateTasks()
    }

    fun returnTranslation(dx: Float, dy: Float) {
        translationX = (translationX + dx)
//                .coerceIn(minTranslationX, 0f)
        translationY = (translationY + dy)
//                .coerceIn(minTranslationY, 0f)
        tableView.invalidate()
    }

    fun recalculate() {
        updateTasks()
    }

    private fun updateTasks() {
        // Подготовка матрицы для трансформации фигур тасок
        with(matrix) {
            reset()
            // Порядок имеет значение
//                postScale(scaleX, 1f)
            postTranslate(translationX, translationY)
        }
        tableView.dataUi.forEach {
            it.forEach {dataUi ->
                dataUi ?: return@forEach
                dataUi.transform(matrix)
            }
        }
        tableView.invalidate()
    }
}