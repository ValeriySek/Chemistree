package com.example.adapter.a

import android.os.Handler
import java.util.concurrent.Executors

internal abstract class BaseAsyncDiffer(
    diffResultApplier: DiffResultApplier,
    diffCallbackCreator: DiffCallbackCreator
) : BaseDiffer(diffResultApplier, diffCallbackCreator), AsyncDiffer {

    private val handler = Handler()

    private val diffExecutor = Executors.newFixedThreadPool(DIFF_EXECUTOR_POOL_SIZE)

    override fun startDiffCalculation(diffCalculationBundle: DiffCalculationBundle) {
        diffExecutor.execute {
            computeDiff(diffCalculationBundle)
        }
    }

    override fun dispatchDiffResult(diffResultBundle: DiffResultBundle) {
        handler.post { applyDiffResult(diffResultBundle) }
    }

    private companion object {

        const val DIFF_EXECUTOR_POOL_SIZE = 2
    }
}