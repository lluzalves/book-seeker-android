package com.ciandt.book.seeker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ciandt.book.seeker.R
import com.daniel.domain.dto.ItunesItem

class ItunesItemAdapter constructor(private val items: List<ItunesItem>) : RecyclerView.Adapter<ItunesItemViewHolder>() {

    private lateinit var holder: ItunesItemViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItunesItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_item, null)
        holder = ItunesItemViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ItunesItemViewHolder, position: Int) {
        holder.show(items[position])
    }
}