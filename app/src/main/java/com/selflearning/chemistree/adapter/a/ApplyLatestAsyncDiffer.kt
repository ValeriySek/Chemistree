package com.example.adapter.a

import java.util.*

internal class ApplyLatestAsyncDiffer(
    diffResultApplier: DiffResultApplier,
    diffCallbackCreator: DiffCallbackCreator
) : BaseAsyncDiffer(diffResultApplier, diffCallbackCreator) {

    private val pendingUpdates: Deque<DiffCalculationBundle> = ArrayDeque()

    override fun calculateDiffInternal(diffCalculationBundle: DiffCalculationBundle) {
        pendingUpdates.push(diffCalculationBundle)
        if (pendingUpdates.size > 1) return

        startDiffCalculation(diffCalculationBundle)
    }

    override fun applyDiffResult(diffResultBundle: DiffResultBundle) {
        pendingUpdates.remove(diffResultBundle.calculationBundle)
        if (pendingUpdates.isNotEmpty()) {
            val latest = pendingUpdates.pop()
            pendingUpdates.clear()
            startDiffCalculation(latest)
        } else {
            diffResultApplier.apply(diffResultBundle)
        }
    }
}