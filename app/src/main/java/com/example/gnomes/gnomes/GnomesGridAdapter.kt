package com.example.gnomes.network

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gnomes.databinding.GridViewItemBinding

class GnomesGridAdapter : ListAdapter<GnomeModel, GnomesGridAdapter.GnomeModelViewHolder>(DiffCallback){

    class GnomeModelViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gnomeModel: GnomeModel) {
            binding.gnomeModel = gnomeModel
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<GnomeModel>(){
        override fun areItemsTheSame(oldItem: GnomeModel, newItem: GnomeModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GnomeModel, newItem: GnomeModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup
                                    , viewType: Int): GnomesGridAdapter.GnomeModelViewHolder {
        return GnomeModelViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GnomeModelViewHolder, position: Int) {
        val gnomeModel = getItem(position)
        holder.bind(gnomeModel)
    }
}