package com.daniel.data.entities

import com.google.gson.annotations.SerializedName

data class ItunesItemEntity(
    @SerializedName(FILE_SIZE)
    val fileSize: Int = 0,
    @SerializedName(CENSORED_NAME)
    val censoredName: String,
    @SerializedName(ARTIST_URL)
    val artistUrl: String,
    @SerializedName(TRACK_VIEW_URL)
    val trackUrl: String,
    @SerializedName(ARTWORK_URL60)
    val artwork60: String,
    @SerializedName(ARTWORK_URL100)
    val artwork100: String,
    @SerializedName(GENRE_IDS)
    val itemGenreIds: List<String>,
    @SerializedName(RELEASED_DATE)
    val releasedAt: String,
    @SerializedName(TRACK_ID)
    val id: Int = 0,
    @SerializedName(TRACK_NAME)
    val name: String,
    @SerializedName(FORMATTED_PRICE)
    val itemFormattedPrice: String,
    @SerializedName(ARTIST_IDS)
    val itemArtistIds: List<Int>,
    @SerializedName(CURRENCY)
    val itemCurrency: String,
    @SerializedName(KIND)
    val typeOfItunesItem: String,
    @SerializedName(ARTIST_ID)
    val itemArtistId: Int = 0,
    @SerializedName(ARTIST_NAME)
    val artist: String,
    @SerializedName(GENRES)
    val genreNames: List<String>,
    @SerializedName(PRICE)
    val itemPrice: Double = 0.0,
    @SerializedName(DESCRIPTION)
    val itemDescription: String
) {
    companion object {
        const val FILE_SIZE = "fileSizeBytes"
        const val CENSORED_NAME = "trackCensoredName"
        const val ARTIST_URL = "artistViewUrl"
        const val TRACK_VIEW_URL = "trackViewUrl"
        const val ARTWORK_URL60 = "artworkUrl60"
        const val ARTWORK_URL100 = "artworkUrl100"
        const val GENRE_IDS = "genreIds"
        const val RELEASED_DATE = "releaseDate"
        const val TRACK_ID = "trackId"
        const val TRACK_NAME = "trackName"
        const val FORMATTED_PRICE = "formattedPrice"
        const val ARTIST_IDS = "artistIds"
        const val ARTIST_ID = "artistId"
        const val ARTIST_NAME = "artistName"
        const val KIND = "kind"
        const val CURRENCY = "currency"
        const val GENRES = "genres"
        const val PRICE = "price"
        const val DESCRIPTION = "description"
    }
}