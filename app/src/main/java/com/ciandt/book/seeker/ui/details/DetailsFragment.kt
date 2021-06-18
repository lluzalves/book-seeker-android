package com.ciandt.book.seeker.ui.details

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.ciandt.book.seeker.R
import com.daniel.commons.DateConverter
import com.daniel.domain.dto.ItunesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cell_item.view.*
import kotlinx.android.synthetic.main.fragment_item_details.*

class DetailsFragment : Fragment() {

    private lateinit var itunesItem: ItunesItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_details, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        itunesItem = bundle?.getSerializable("selectedItem") as (ItunesItem)
        setupUiItems()
    }

    private fun setupUiItems() {
        Picasso.get()
            .load(itunesItem.artwork100)
            .into(itemCover as ImageView)

        itemReleaseDate.text = itunesItem.releasedAt?.let { DateConverter.formatDate(it) }
        itunesItem.typeOfItunesItem.apply {
            typeOfItem.text = this ?: "-"
        }
        itunesItem.name.apply {
            itemName.text = this ?: "-"
        }
        itunesItem.artist.apply {
            authorName.text = this ?: "-"
        }
        itunesItem.fileSize.apply {
            itemSize.text = this.toLong().toString()
        }
        itemDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(itunesItem.itemDescription, HtmlCompat.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(itunesItem.itemDescription)
        }
    }

}