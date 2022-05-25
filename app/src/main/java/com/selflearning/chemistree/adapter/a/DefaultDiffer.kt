package com.example.adapter.a

internal class DefaultDiffer(
    diffResultApplier: DiffResultApplier,
    diffCallbackCreator: DiffCallbackCreator
) : BaseDiffer(diffResultApplier, diffCallbackCreator) {

    override fun dispatchDiffResult(diffResultBundle: DiffResultBundle) {
        applyDiffResult(diffResultBundle)
    }
}