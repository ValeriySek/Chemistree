package com.example.adapter.a

internal interface Differ {

    /**
     * Initiate a diff calculation process.
     *
     * @param diffCalculationBundle bundle with data required for diff calculating.
     */
    fun calculateDiff(diffCalculationBundle: DiffCalculationBundle)
}