package com.lukdro.dermediaplayer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lukdro.dermediaplayer.ListClickListener
import com.lukdro.dermediaplayer.R
import com.lukdro.dermediaplayer.data.IMusicRepo

class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textField: TextView = itemView.findViewById(R.id.justATemporaryTf)
}

class TunesListAdapter(
    private val musicRepo: IMusicRepo,
    private val clickListener: ListClickListener
    ) : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tunes_list_item, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.textField.text = musicRepo[position].name
        val itemView = holder.itemView
        itemView.setOnClickListener { clickListener.onClick(itemView, position) }
    }

    override fun getItemCount(): Int = musicRepo.size()
}