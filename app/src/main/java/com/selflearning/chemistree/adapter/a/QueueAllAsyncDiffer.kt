package com.example.adapter.a

import java.util.*

internal class QueueAllAsyncDiffer(
    diffResultApplier: DiffResultApplier,
    diffCallbackCreator: DiffCallbackCreator
) : BaseAsyncDiffer(diffResultApplier, diffCallbackCreator) {

    private val pendingUpdates = ArrayDeque<DiffCalculationBundle>()

    override fun calculateDiffInternal(diffCalculationBundle: DiffCalculationBundle) {
        pendingUpdates.add(diffCalculationBundle)
        if (pendingUpdates.size > 1) return

        startDiffCalculation(diffCalculationBundle)
    }

    override fun applyDiffResult(diffResultBundle: DiffResultBundle) {
        pendingUpdates.remove()
        diffResultApplier.apply(diffResultBundle)
        if (pendingUpdates.isNotEmpty()) {
            startDiffCalculation(pendingUpdates.peek())
        }
    }
}