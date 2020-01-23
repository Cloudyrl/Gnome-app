package com.example.gnomes.gnomes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gnomes.databinding.GridViewItemBinding
import com.example.gnomes.network.GnomeModel

class GnomesGridAdapter(val onClickListener: OnClickListener) : ListAdapter<GnomeModel, GnomesGridAdapter.GnomeModelViewHolder>(
    DiffCallback
){

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
                                    , viewType: Int): GnomeModelViewHolder {
        return GnomeModelViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: GnomeModelViewHolder, position: Int) {
        val gnomeModel = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(gnomeModel)
        }
        holder.bind(gnomeModel)
    }

    class OnClickListener(val clickListener: (gnomeModel: GnomeModel) -> Unit) {
        fun onClick(gnomeModel:GnomeModel) = clickListener(gnomeModel)
    }

}