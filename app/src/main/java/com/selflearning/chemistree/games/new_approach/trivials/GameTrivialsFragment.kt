package com.selflearning.chemistree.games.new_approach.trivials

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.selflearning.chemistree.core.adapter.BaseAdapter
import com.selflearning.chemistree.core.adapter.BindableItem
import com.selflearning.chemistree.databinding.FragmentBlankBinding
import com.selflearning.chemistree.domain.chemistry.trivials.Trivial
import com.selflearning.chemistree.games.models.GameModel
import com.selflearning.chemistree.games.new_approach.GameFragmentWithActions
import com.selflearning.chemistree.games.new_approach.GameRepository

class GameTrivialsFragment : GameFragmentWithActions() {

    override val gameRepository: GameRepository
        get() = GameTrivialsRepository()

    private var baseAdapter = BaseAdapter()



    private lateinit var binding: FragmentBlankBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
    }

    override fun onStartGame(data: GameModel) {
        super.onStartGame(data)
        binding.trivialsQuestionTv.text = data.gameQuestion.question
        val list = data.auxiliaryList.map {
            BindableItem(it, GameTrivialButtonController { answer->
                gameViewModelWithActions.answer(answer)
            })
        }
        baseAdapter.add(list)
    }

    override fun onRightAnswer() {

        Handler(Looper.getMainLooper()).postDelayed({
            gameViewModelWithActions.getQuestion()
        }, 500)
    }

    override fun onWrongAnswer(data: Any) {
        data as? Trivial
        Handler(Looper.getMainLooper()).postDelayed({
             gameViewModelWithActions.getQuestion()
        }, 500)
    }

    override fun onFinishGame() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            trivialsButtonsRv.apply {
                adapter = baseAdapter
            }
        }
    }



    companion object {

        @JvmStatic
        fun newInstance() =
            GameTrivialsFragment()
    }
}