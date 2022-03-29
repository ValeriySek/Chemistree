package com.selflearning.chemistree.f_mendeleev_table.table_view

import android.graphics.Matrix
import android.util.Log

class Transforms(
    val tableView: TableView
) {

    val viewPortHandler = tableView.viewPortHandler
    private val matrix = Matrix()

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
        Log.i("TAGGGGGGG", "addTranslation dx $dx translationX $translationX")
//        tableView.invalidate()
        updateTasks()
    }

    fun setScale(scale: Float, dx: Float, dy: Float) {
        this.scale = scale
        tableView.updateDataRects(scale)
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
}