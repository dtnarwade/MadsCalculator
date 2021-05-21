package com.example.themadscalculator.ui.fragments.mads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themadscalculator.R
import com.example.themadscalculator.databinding.MadsFragemntFragmentBinding

class MadsFragemnt : Fragment(), ButtonsAdapter.ButtonListener {

    var binding: MadsFragemntFragmentBinding? = null
    private lateinit var viewModel: MadsFragemntViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MadsFragemntFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MadsFragemntViewModel::class.java)
        val buttonLabels = resources.getStringArray(R.array.buttons)

        binding?.buttonsRv?.apply {
            layoutManager = GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = ButtonsAdapter(buttonLabels, this@MadsFragemnt)

        }

        viewModel.operationText.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding?.operationTv?.text = it
            }
        })
        viewModel.resultText.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding?.resultTv?.text = it
            }
        })
        binding?.historyTv?.setOnClickListener {
            findNavController(this@MadsFragemnt).navigate(R.id.HistoryFragemnt)
        }
        return binding?.root
    }

    override fun onclick(label: String) {
        if (!label.equals("AC")) {
            viewModel.onButtonClick(label)
        } else {
            viewModel.allClear()

        }

    }

}
