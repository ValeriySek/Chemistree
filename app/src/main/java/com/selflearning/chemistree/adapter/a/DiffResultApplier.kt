package com.example.adapter.a

import androidx.recyclerview.widget.DiffUtil.DiffResult

internal fun interface DiffResultApplier {

    /**
     * Apply calculated [DiffResult].
     *
     * @param diffResultBundle bundle with calculated [DiffResult] and additional information.
     */
    fun apply(diffResultBundle: DiffResultBundle)
}