package com.selflearning.chemistree.f_games.gamespack

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.selflearning.chemistree.R
import com.selflearning.chemistree.adapter.GamesPackAdapter
import com.selflearning.chemistree.games.GameComponentsList
import java.util.*

class GamesPack : Fragment() {
    private var activity: Activity? = null
    private var adapter: GamesPackAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private val rowTypeList: MutableList<RowType> = ArrayList()
    private lateinit var gameComponentsList: GameComponentsList
    private var mViewModel: GamesPackViewModel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.games_pack_fragment, container, false)
        initVar()
        initView(root)
        initListener()
        return root
    }

    private fun initVar() {
        activity = requireActivity()
        gridLayoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        gameComponentsList = GameComponentsList()
        rowTypeList.add(DividerRowType("ORGANIC"))
        rowTypeList.add(GamesRowType(requireActivity(), gameComponentsList.gameComponentsList[0]))
        rowTypeList.add(GamesRowType(requireActivity(), gameComponentsList.gameComponentsList[4]))
        rowTypeList.add(GamesRowType(requireActivity(), gameComponentsList.gameComponentsList[5]))
        rowTypeList.add(GamesRowType(requireActivity(), gameComponentsList.gameComponentsList[2]))
        rowTypeList.add(GamesRowType(requireActivity(), gameComponentsList.gameComponentsList[2]))
        rowTypeList.add(DividerRowType("INORGANIC"))
        rowTypeList.add(GamesRowType(requireActivity(), gameComponentsList.gameComponentsList[0]))
        rowTypeList.add(GamesRowType(requireActivity(), gameComponentsList.gameComponentsList[1]))
        rowTypeList.add(GamesRowType(requireActivity(), gameComponentsList.gameComponentsList[2]))
        rowTypeList.add(GamesRowType(requireActivity(), gameComponentsList.gameComponentsList[3]))
        rowTypeList.add(GamesRowType(requireActivity(), gameComponentsList.gameComponentsList[4]))
        rowTypeList.add(GamesRowType(requireActivity(), gameComponentsList.gameComponentsList[5]))
        gridLayoutManager!!.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (rowTypeList[position] is DividerRowType) {
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
            adapter = GamesPackAdapter(rowTypeList, requireContext())
            adapter = adapter
        }
    }

    private fun initListener() {}
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(GamesPackViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance(): GamesPack {
            return GamesPack()
        }
    }
}