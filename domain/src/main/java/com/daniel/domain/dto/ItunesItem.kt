package com.daniel.domain.dto

import java.io.Serializable

class ItunesItem(
 var fileSize: Int = 0,
 var censoredName: String?,
 var artistUrl: String?,
 var trackUrl: String?,
 var artwork60: String?,
 var artwork100: String?,
 var itemGenreIds: List<String?>,
 var releasedAt: String?,
 var id: Int = 0,
 var name: String?,
 var formattedPrice: String?,
 var itemArtistIds: List<Int>,
 var itemCurrency: String?,
 var typeOfItunesItem: String?,
 var itemArtistId: Int = 0,
 var artist: String?,
 var genreNames: List<String?>,
 var itemPrice: Double = 0.0,
 var itemDescription: String?
) : Serializable {
}