package com.example.themadscalculator.ui.fragments.mads

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themadscalculator.databinding.ButtonLayoutBinding

class ButtonsAdapter(var labels: Array<String>,var listener: ButtonListener) : RecyclerView.Adapter<ButtonsAdapter.ButtonHolder>() {
    class ButtonHolder(private val itemBinding: ButtonLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(label: String, listener: ButtonListener) {
            itemBinding.buttonText.text = label
            itemBinding.buttonMainLayout.setOnClickListener{
                listener.onclick(label)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonHolder {
        val itemBinding =
            ButtonLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ButtonHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ButtonHolder, position: Int) {
        holder.bind(labels[position],listener)
    }

    override fun getItemCount(): Int {
        return labels.size
    }

    public interface ButtonListener{
        fun onclick(label:String)
    }
}