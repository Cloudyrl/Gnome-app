package com.example.gnomes.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gnomes.R
import kotlinx.android.synthetic.main.list_item.view.*

//Adaptador del RecyclerView de la lista de profesiones y amigos
class GnomeListAdapter(val items: List<String>, val context: Context) :
    RecyclerView.Adapter<GnomeListAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val listText = view.item_list_text
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.listText.text = items.get(position)
    }
}
