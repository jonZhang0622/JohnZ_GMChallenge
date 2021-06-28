package com.example.johnz_gmchallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.johnz_gmchallenge.databinding.SongItemBinding
import com.example.johnz_gmchallenge.model.Result

class ItunesAdapter : RecyclerView.Adapter<ItunesAdapter.ItunesViewHolder>() {

    private val dataSet = mutableListOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItunesViewHolder {
        val binding = SongItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItunesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItunesViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    fun setList(results: List<Result>) {
        dataSet.clear()
        dataSet.addAll(results)
        notifyDataSetChanged()
    }

    class ItunesViewHolder(private val binding: SongItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) = with(binding) {
            album = result
            executePendingBindings()
        }
    }
}