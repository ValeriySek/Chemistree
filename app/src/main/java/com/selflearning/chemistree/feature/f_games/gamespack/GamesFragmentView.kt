package com.selflearning.chemistree.feature.f_games.gamespack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.selflearning.chemistree.R
import com.selflearning.chemistree.core.adapter.BaseAdapter
import com.selflearning.chemistree.core.adapter.BindableItem
import com.selflearning.chemistree.databinding.GamesPackFragmentBinding
import com.selflearning.chemistree.feature.f_games.gamespack.controller.GamesController
import com.selflearning.chemistree.feature.f_games.gamespack.controller.GamesDividerController
import com.selflearning.chemistree.games.GameComponentsList
import com.selflearning.chemistree.games.before_game.PreGameActivity
import com.selflearning.chemistree.utilities.ActivityUtilities

class GamesFragmentView : Fragment() {

    private var baseAdapter = BaseAdapter()
    private var recyclerView: RecyclerView? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private val rowTypeList = mutableListOf<BindableItem>()
    private lateinit var gameComponentsList: GameComponentsList
//    private var mViewModel: GamesPackViewModel? = null

    private lateinit var binding: GamesPackFragmentBinding

    private val gamesController = GamesController {
        ActivityUtilities.invokeNewActivity(
                requireActivity(),
                PreGameActivity::class.java,
                it,
                false
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = GamesPackFragmentBinding.inflate(inflater, container, false)
        initVar()
        initView(binding.root)
        initListener()
        return binding.root
    }

    private fun initVar() {
        gridLayoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        gameComponentsList = GameComponentsList()

        rowTypeList.apply {
            add(BindableItem(DividerRowType("ORGANIC"), GamesDividerController()))
            add(BindableItem(GamesRowType(gameComponentsList.gameComponentsList[0]), gamesController))
            add(BindableItem(GamesRowType(gameComponentsList.gameComponentsList[4]), gamesController))
            add(BindableItem(GamesRowType(gameComponentsList.gameComponentsList[5]), gamesController))
            add(BindableItem(GamesRowType(gameComponentsList.gameComponentsList[2]), gamesController))
            add(BindableItem(GamesRowType(gameComponentsList.gameComponentsList[2]), gamesController))
            add(BindableItem(DividerRowType("INORGANIC"), GamesDividerController()))
            add(BindableItem(GamesRowType(gameComponentsList.gameComponentsList[0]), gamesController))
            add(BindableItem(GamesRowType(gameComponentsList.gameComponentsList[1]), gamesController))
            add(BindableItem(GamesRowType(gameComponentsList.gameComponentsList[2]), gamesController))
            add(BindableItem(GamesRowType(gameComponentsList.gameComponentsList[3]), gamesController))
            add(BindableItem(GamesRowType(gameComponentsList.gameComponentsList[4]), gamesController))
            add(BindableItem(GamesRowType(gameComponentsList.gameComponentsList[5]), gamesController))
        }

        gridLayoutManager!!.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (rowTypeList[position].itemController is GamesDividerController) {
                    2
                } else 1
            }
        }
    }

    private fun initView(view: View) {
        recyclerView = view.findViewById(R.id.rvGamesPack)
        recyclerView?.apply {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            setItemViewCacheSize(10)
            adapter = baseAdapter
            baseAdapter.add(rowTypeList)
        }
    }

    private fun initListener() {}
}