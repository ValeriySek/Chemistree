package com.selflearning.chemistree.games.new_approach.trivials

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.get
import com.selflearning.chemistree.adapter.ItemAnim
import com.selflearning.chemistree.core.adapter.BaseAdapter
import com.selflearning.chemistree.core.adapter.BindableItem
import com.selflearning.chemistree.databinding.FragmentBlankBinding
import com.selflearning.chemistree.games.new_approach.GameFragmentWithActions
import com.selflearning.chemistree.games.new_approach.GameRepository
import com.selflearning.chemistree.games.new_approach.game_utils.Shakable

class GameTrivialsFragment : GameFragmentWithActions<GameState>(), Shakable {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            trivialsButtonsRv.apply {
                adapter = baseAdapter
                itemAnimator = ItemAnim()
            }
        }
    }

    override fun onNewQuestion(data: GameState) {

        val gameModel = data.gameModel

        binding.trivialsQuestionTv.text = gameModel.gameQuestion.question
        updateAnswers(gameModel.auxiliaryList)
    }

    override fun onRightAnswer(data: GameState) {
        onAnswer(data)
    }

    override fun onWrongAnswer(data: GameState) {
        updateLives(data.wastedLives)
        onAnswer(data)
    }

    private fun onAnswer(data: GameState) {
        updateScore(data.score)
        updateAnswers(data.gameModel.auxiliaryList)
        Handler(Looper.getMainLooper()).postDelayed({
            gameViewModelWithActions.getQuestion()
        }, 1000)
    }

    override fun onFinishGame(data: GameState) {
        Toast.makeText(requireContext(), "Game End", Toast.LENGTH_SHORT).show()
    }

    private fun updateLives(lives: Int) {

        val livesContainer = binding.trivialsLivesContainerLl

        for (i in 0 until lives) {
            val child = livesContainer[i] as ImageView
            child.drawable.changeColorOfDrawable(Color.LTGRAY)
        }

    }

    private fun updateScore(score: Int) {
        binding.trivialsScoreTv.text = score.toString()
    }

    private fun updateAnswers(answers: List<GameAnswerData>) {
        val list = answers.map {
            BindableItem(it, GameTrivialButtonController { answer->
                gameViewModelWithActions.answer(answer)
            })
        }
        baseAdapter.add(list)
    }

    private fun Drawable.changeColorOfDrawable(color: Int) {
        val wrappedDrawable: Drawable = DrawableCompat.wrap(this)
        DrawableCompat.setTint(wrappedDrawable, color)
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            GameTrivialsFragment()
    }
}