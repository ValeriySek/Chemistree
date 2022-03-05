package com.selflearning.chemistree.f_mendeleev_table.table_view

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat

fun View.getColor(color: Int) = ContextCompat.getColor(context, color)

fun View.getDimension(dimen: Int) = resources.getDimension(dimen)

fun Context.getDimension(dimen: Int) = resources.getDimension(dimen)

fun Context.getDensity() = this.resources.displayMetrics.scaledDensity