package com.selflearning.chemistree.views.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Размер системных окон
 *
 * @param statusBarSize размер системного статус-бара
 * @param navigationBarSize размер navigation-бара
 * @param keyboardSize размер системной клавиатуры
 */
@Parcelize
data class SystemBarsSize(
    val statusBarSize: Int = 0,
    val navigationBarSize: Int = 0,
    val keyboardSize: Int = 0
) : Parcelable {

    val isEmpty get() = statusBarSize == 0 && navigationBarSize == 0 && keyboardSize == 0
}
