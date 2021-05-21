package com.example.themadscalculator.ui.fragments.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themadscalculator.model.History

import com.example.themadscalculator.databinding.HistoryFragmentBinding
import com.example.themadscalculator.ui.fragments.mads.MadsFragemntViewModel

class HistoryFragment : Fragment(), HistoryAdapter.HistoryClickListener {

    private lateinit var viewModel: MadsFragemntViewModel
    var binding: HistoryFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HistoryFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MadsFragemntViewModel::class.java)
        binding?.historyRv?.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter =  HistoryAdapter(viewModel.historyList, this@HistoryFragment)
        }

        return binding?.root
    }

    override fun onHistoryClick(history: History) {
        viewModel.setFromHistory(history)
        findNavController(this@HistoryFragment).popBackStack()
    }

}
