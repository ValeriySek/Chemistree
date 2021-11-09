package com.selflearning.chemistree.utils.extentions

import android.view.View
import kotlinx.coroutines.*

fun View.viewWidth(int: Int){
    this.layoutParams.width = (this.resources.displayMetrics.density * int).toInt()
}

inline fun <T> View.doAsync(
        crossinline backgroundTask: (scope: CoroutineScope) -> T,
        crossinline result: (T?) -> Unit) {
    val job = CoroutineScope(Dispatchers.Main)
    // Добавляем слушатель, который будет отменять
    // корутину, если вьюха откреплена
    val attachListener = object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(p0: View?) {}
        override fun onViewDetachedFromWindow(p0: View?) {
            job.cancel()
            removeOnAttachStateChangeListener(this)
        }
    }
    this.addOnAttachStateChangeListener(attachListener)
    // Создаем Job, которая будет работать в основном потоке
    job.launch {
        // Создаем Deferred с результатом в фоновом потоке
        val data = async(Dispatchers.Default) {
            try {
                backgroundTask(this)
            } catch (e: Exception) {
                e.printStackTrace()
                return@async null
            }
        }
        if (isActive) {
            try {
                result.invoke(data.await())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        // Отписываем слушатель по окончании Job
        this@doAsync.removeOnAttachStateChangeListener(attachListener)
    }
}