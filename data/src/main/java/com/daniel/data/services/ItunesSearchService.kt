package com.daniel.data.services

import com.daniel.data.services.ItunesApiUrls.ItunesSearchApi
import com.daniel.data.services.ItunesApiUrls.ItunesSearchApiUrls
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ItunesSearchService {
    @GET(ItunesSearchApiUrls.SEARCH_BOOKS)
    fun findBookItem(@QueryMap options: Map<String, String>) : Single<ItunesSearchResponse>

    @GET(ItunesSearchApi.BASE_URL)
    fun findItem(@Query(ItunesSearchApi.TERM) searchTerm : String) : Single<ItunesSearchResponse>

}