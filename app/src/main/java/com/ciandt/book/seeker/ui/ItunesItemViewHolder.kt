package com.ciandt.book.seeker.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.navigation.Navigation
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.base.BaseViewHolder
import com.daniel.commons.DateConverter
import com.daniel.domain.dto.ItunesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cell_item.view.*


class ItunesItemViewHolder constructor(itemView: View) :
    BaseViewHolder<ItunesItem>(itemView),
    View.OnClickListener {
    private lateinit var itunesItem: ItunesItem

    override fun show(model: ItunesItem) {
        itunesItem = model
        itunesItem.name.apply {
            itemView.bookTitle.text = this ?: "-"
        }
        itunesItem.artist.apply {
            itemView.author.text = this ?: "-"
        }
        itemView.price.text = itunesItem.formattedPrice.apply {
            itemView.price.text = this ?: "-"
        }
        itemView.releaseDate.text = itunesItem.releasedAt?.let { DateConverter.formatDate(it) }
        itunesItem.artwork100?.let { setItemArtwork(it) }
        addToClickListener()
        itemView.setOnClickListener(this)
    }

    override fun addToClickListener() {
        itemView.bookTitle.setOnClickListener(this)
        itemView.author.setOnClickListener(this)
        itemView.price.setOnClickListener(this)
        itemView.releaseDate.setOnClickListener(this)
        itemView.itemLayout.setOnClickListener(this)
    }

    private fun setItemArtwork(path: String) {
        Picasso.with(itemView.context)
            .load(path)
            .into(itemView.itemCover as ImageView)
    }

    override fun onClick(v: View) {
        val bundle = Bundle()
        bundle.putSerializable("selectedItem", itunesItem)
        Navigation.findNavController(itemView)
            .navigate(R.id.action_searchFragment_to_detailsFragment, bundle)

    }
}