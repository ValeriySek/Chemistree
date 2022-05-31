package com.selflearning.chemistree.games.new_approach.trivials

import android.graphics.Color
import android.util.Log
import android.view.ViewGroup
import com.selflearning.chemistree.core.adapter.BindableItemController
import com.selflearning.chemistree.core.adapter.BindableViewHolder
import com.selflearning.chemistree.games.new_approach.game_utils.Shakable
import com.selflearning.chemistree.views.buttons.SharpMaterialButton

class GameTrivialButtonController(
    private val onClick: (String) -> Unit
) : BindableItemController(), Shakable {

    override fun createViewHolder(parent: ViewGroup): BindableViewHolder {
        val view = SharpMaterialButton(parent.context)
        return Holder(view)
    }

    inner class Holder(val view: SharpMaterialButton) : BindableViewHolder(view) {

        private lateinit var question: String

        init {
            view.setOnClickListener {
                onClick(question)
            }
        }

        override fun bind(data: Any) {
            data as GameAnswerData
            question = data.answerVariant
            view.text = data.answerVariant
            view.isClickable = data.isClickable
            Log.i("TAGGG", "data.bind $question")
            data.isCorrect?.let {isCorrect->
                Log.i("TAGGG", "data.isCorrect")
                view.setBackgroundColor(if (isCorrect) Color.GREEN else Color.RED)
                if (!isCorrect) view.shake()
            }
        }

    }
}


//class GameTrivialButtonController(
//    private val onClick: (String) -> Unit
//) : BindableItemController<GameTrivialAnswerData, GameTrivialButtonController.Holder>(), Shakable {
//
//    override fun getItemId(data: GameTrivialAnswerData) = "${data.answerVariant}${data.isCorrect}${data.question}"
//
//    override fun createViewHolder(parent: ViewGroup): Holder {
//        val view = SharpMaterialButton(parent.context)
//        return Holder(view)
//    }
//
//    inner class Holder(val view: SharpMaterialButton) : BindableViewHolder<GameTrivialAnswerData>(view) {
//
//        private lateinit var question: String
//
//        init {
//            view.setOnClickListener {
//                onClick(question)
//            }
//        }
//
//        override fun bind(data: GameTrivialAnswerData) {
//            question = data.answerVariant
//            view.text = data.answerVariant
//            if (data.isCorrect == null) {
//                view.setBackgroundColor(Color.BLACK)
//            } else {
//                view.setBackgroundColor(if (data.isCorrect) Color.GREEN else Color.RED)
//                if (!data.isCorrect) view.shake()
//            }
//        }
//
//    }
//}