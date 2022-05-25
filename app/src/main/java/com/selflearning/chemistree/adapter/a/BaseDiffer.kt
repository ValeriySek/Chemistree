package com.example.adapter.a

import androidx.recyclerview.widget.DiffUtil

internal abstract class BaseDiffer(
    protected val diffResultApplier: DiffResultApplier,
    private val diffCallbackCreator: DiffCallbackCreator
) : Differ {

    override fun calculateDiff(diffCalculationBundle: DiffCalculationBundle) {
        calculateDiffInternal(diffCalculationBundle)
    }

    /**
     * Dispatches diff result to the main thread.
     */
    protected abstract fun dispatchDiffResult(diffResultBundle: DiffResultBundle)

    protected open fun calculateDiffInternal(diffCalculationBundle: DiffCalculationBundle) {
        startDiffCalculation(diffCalculationBundle)
    }

    protected open fun startDiffCalculation(diffCalculationBundle: DiffCalculationBundle) {
        computeDiff(diffCalculationBundle)
    }

    protected open fun applyDiffResult(diffResultBundle: DiffResultBundle) {
        diffResultApplier.apply(diffResultBundle)
    }

    protected fun computeDiff(diffCalculationBundle: DiffCalculationBundle) {
        val diffResult = DiffUtil.calculateDiff(
            diffCallbackCreator.createDiffCallback(
                diffCalculationBundle.oldItemInfo,
                diffCalculationBundle.newItemInfo
            )
        )
        dispatchDiffResult(DiffResultBundle(diffResult, diffCalculationBundle))
    }
}