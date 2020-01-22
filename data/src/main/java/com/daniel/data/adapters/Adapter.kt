package com.daniel.data.adapters

import com.daniel.data.entities.ItunesItemEntity
import com.daniel.domain.dto.ItunesItem

object ItunesItemAdapter {
    fun toItunesItem(itunesItem: ItunesItemEntity) = ItunesItem(
        fileSize = itunesItem.fileSize,
        censoredName = itunesItem.censoredName,
        artistUrl = itunesItem.artistUrl,
        trackUrl = itunesItem.trackUrl,
        itemDescription = itunesItem.itemDescription,
        artist = itunesItem.artist,
        releasedAt = itunesItem.releasedAt,
        artwork60 = itunesItem.artwork60,
        name = itunesItem.name,
        formattedPrice = itunesItem.itemFormattedPrice,
        genreNames = itunesItem.genreNames,
        typeOfItunesItem = itunesItem.typeOfItunesItem,
        artwork100 = itunesItem.artwork100,
        itemArtistId = itunesItem.itemArtistId,
        itemPrice = itunesItem.itemPrice,
        itemCurrency = itunesItem.itemCurrency,
        itemArtistIds = itunesItem.itemArtistIds,
        itemGenreIds = itunesItem.itemGenreIds
    )
}