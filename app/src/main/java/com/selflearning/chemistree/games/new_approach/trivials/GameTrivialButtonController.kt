package com.selflearning.chemistree.games.new_approach.trivials

import android.view.View
import android.view.ViewGroup
import com.selflearning.chemistree.core.adapter.BindableItemController
import com.selflearning.chemistree.core.adapter.BindableViewHolder
import com.selflearning.chemistree.views.buttons.SharpMaterialButton

class GameTrivialButtonController(
    private val onClick: (String) -> Unit
) : BindableItemController() {

    override fun createViewHolder(parent: ViewGroup): BindableViewHolder {
        val view = SharpMaterialButton(parent.context)
        return Holder(view)
    }

    inner class Holder(val view: SharpMaterialButton) : BindableViewHolder(view) {

        override fun bind(data: Any) {
            data as GameTrivialAnswerData
            view.text = data.question
        }

    }
}