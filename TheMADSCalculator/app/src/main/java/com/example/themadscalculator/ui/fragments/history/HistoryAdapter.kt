package com.example.themadscalculator.ui.fragments.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themadscalculator.model.History
import com.example.themadscalculator.databinding.HistoryAdapterLayoutBinding

class HistoryAdapter(
    var historyList: ArrayList<History>,
    var historyListener: HistoryClickListener
) : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    class HistoryHolder(private val itemBinding: HistoryAdapterLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(histry: History, historyListener: HistoryClickListener) {
            itemBinding.Opration.text = histry.opration
            itemBinding.result.text = "=" + histry.result
            itemBinding.historyMainLayout.setOnClickListener {
                historyListener.onHistoryClick(histry)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {

        val itemBinding =
            HistoryAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(historyList[position], historyListener)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    interface HistoryClickListener {
        fun onHistoryClick(history: History)
    }
}