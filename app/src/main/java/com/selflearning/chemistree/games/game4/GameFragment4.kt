package com.selflearning.chemistree.games.game4

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.selflearning.chemistree.ChemistreeApplication
import com.selflearning.chemistree.R
import com.selflearning.chemistree.adapter.GameButtonsAdapter
import com.selflearning.chemistree.databinding.FragmentGame2Binding
import com.selflearning.chemistree.di.view_model.di.ViewModelFactory
import com.selflearning.chemistree.games.BaseGameFragment
import com.selflearning.chemistree.games.GameActivity
import com.selflearning.chemistree.games.GameInterface
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class GameFragment4 : BaseGameFragment() {

    private lateinit var tvAnswer: TextView
    private lateinit var tvQuestionNumber: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var mbSkip: MaterialButton
    private lateinit var mbSubmit: MaterialButton
    private lateinit var mbDelete: MaterialButton
    private lateinit var adapter: GameButtonsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: Game4ViewModel
    private lateinit var binding: FragmentGame2Binding

    private val heartsPack by lazy { arrayListOf(
        binding.imageViewHeart1,
        binding.imageViewHeart2,
        binding.imageViewHeart3
    ) }
    private var lives = 3
    private val numberOfQuestions = 10
    private var question = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGame2Binding.inflate(inflater)
        ((activity as GameActivity).application as ChemistreeApplication).appComponentFactory.inject(
            this
        )

        viewModel = ViewModelProvider(this, viewModelFactory)[Game4ViewModel::class.java]
        gameName = "GameFragment2"

        bindViews(binding)
        initViews()
        loadData()
        initListener()
        return binding.root
    }

    private fun initViews() {
        initRV()
        binding.tvGame3Question.text = "$question / $numberOfQuestions"

        lifecycleScope.launchWhenStarted {

            viewModel.uiState.collect {
                when(it) {
                    is Game4ViewModel.ViewState.Loading -> {
                        Log.i("TAGG", "Loading")
                        mbSubmit.isEnabled = false
                    }
                    is Game4ViewModel.ViewState.Success -> {
                        Log.i("TAGG", "Success  ${it.list}")
                        mbSubmit.isEnabled = true
                    }
                }
            }
        }
//        viewModel.loadAcids().observe(viewLifecycleOwner, { viewModel.setList(it) })
        viewModel.loadElements()
//            .observe(viewLifecycleOwner, { viewModel.setElements(it) })
    }

    private fun initRV() {
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        adapter = GameButtonsAdapter(1)
        recyclerView.adapter = adapter
    }

    private fun bindViews(binding: FragmentGame2Binding) {
        recyclerView = binding.game2FragmentRv
        mbSkip = binding.mbGame3Skip
        mbSubmit = binding.mbGame3Submit
        mbDelete = binding.mbGame3Delete
        tvAnswer = binding.tvGame3Answer
        tvQuestionNumber = binding.tvQuestionNumber
    }

    private fun loadData() {
        viewModel.getMutableStringsRV()
            .observe(requireActivity(), { strings -> adapter.setList(strings) })

        viewModel.getMutableBooleansRV()
            .observe(requireActivity(), { booleans -> adapter.setBooleans(booleans) })

        viewModel.getStringQuestion()
            .observe(requireActivity(), { s -> binding.tvGame3Question.text = s })

        viewModel.getEditTextAnswer().observe(requireActivity(), { s -> tvAnswer.text = s })

        viewModel.isDeleteButtonEnable()
            .observe(requireActivity(), { aBoolean -> mbDelete.isEnabled = aBoolean!! })

        viewModel.getScore().observe(requireActivity(), { s -> binding.tvGame2Score.text = s })
    }

    private fun initListener() {
        adapter.setOnItemClickListener { position: Int ->
            viewModel.changeEnable(
                position,
                adapter.getList()[position]
            )
        }
        mbSkip.setOnClickListener { viewModel.loadData() }
        mbDelete.setOnClickListener { viewModel.deleteLast() }
        mbSubmit.setOnClickListener {
            if (tvAnswer.text == viewModel.formula) {
                onRightAnswer()
            } else {
                onWrongAnswer()
            }
            wait500ms()
        }
    }

    private fun onRightAnswer() {
        tvQuestionNumber.text = "${++question} / $numberOfQuestions"
        score = viewModel.setScore()
        Toast.makeText(activity, "true", Toast.LENGTH_SHORT).show()
        tvAnswer.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark))
    }

    private fun onWrongAnswer() {
        increaseLives()
        viewModel.setAllEnabled()
        mbDelete.isEnabled = false
        mbSkip.isEnabled = false
        mbSubmit.isEnabled = false
        Toast.makeText(activity, "false", Toast.LENGTH_SHORT).show()
        tvAnswer.text = viewModel.formula
        tvAnswer.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
    }

    private fun increaseLives() {
        heartsPack[lives - 1].setImageDrawable(requireContext().resources.getDrawable(R.drawable.ic_heart_filled_white_10))
        --lives
    }

    private fun wait500ms() {
        val handler = Handler()
        handler.postDelayed({ returnOriginalForm() }, 500)
    }

    private fun returnOriginalForm() {
        if (lives == 0 || question == numberOfQuestions) {
            isFinish = true
        }
        tvAnswer.setBackgroundResource(R.drawable.white_stroke_3dp)
        if (!isFinish) {
            viewModel.loadData()
        } else {
            finishGame()
            return
        }
        mbSkip.isEnabled = true
        mbSubmit.isEnabled = true
    }

    companion object {
        private const val TYPE = 1
        fun getInstance(gameInterface: GameInterface?): GameFragment4 {
            gameI = gameInterface
            return GameFragment4()
        }
    }
}