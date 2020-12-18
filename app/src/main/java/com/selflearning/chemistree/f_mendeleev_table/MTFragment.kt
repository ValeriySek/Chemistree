package com.selflearning.chemistree.f_mendeleev_table

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.selflearning.chemistree.adapter.MendAdapter
import com.selflearning.chemistree.chemistry.elements.Element
import com.selflearning.chemistree.databinding.FragmentMTBinding


class MTFragment : Fragment() {

    private var _binding: FragmentMTBinding? = null
    private val binding get() = _binding!!
    val adapter = MendAdapter()
    lateinit var viewModel: MTViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.i("TAGG", "onCreateView start")
        // Inflate the layout for this fragment
        _binding = FragmentMTBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MTViewModel::class.java)
        viewModel.getElements()
        val view = binding.root
        binding.rvMendeleev.adapter = adapter

        Log.i("TAGG", "onCreateView end")
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                MTFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

    override fun onResume() {
        super.onResume()
        Log.i("TAGG", "onResume start")
        viewModel.mutableEl.observe(viewLifecycleOwner, Observer {
//            adapter.ittems(it)
        adapter.items = it as MutableList<Element?>
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}