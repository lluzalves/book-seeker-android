package com.daniel.data.services

import com.daniel.data.entities.ItunesItemEntity
import com.google.gson.annotations.SerializedName

data class ItunesSearchResponse(
    @SerializedName(TOTAL_RESULTS)
    val totalResult: Int = 0,

    @SerializedName(RESULT)
    val result: ArrayList<ItunesItemEntity>
) {
    companion object {
        const val TOTAL_RESULTS = "resultCount"
        const val RESULT = "results"
    }
}