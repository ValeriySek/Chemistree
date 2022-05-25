package com.example.adapter.a

enum class AsyncDiffStrategy {

    /**
     * Skip all pending item list updates except latest.
     */
    APPLY_LATEST,

    /**
     * Add every item list updates in queue and then handle every item list update.
     */
    QUEUE_ALL
}