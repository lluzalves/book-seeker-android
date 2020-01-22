package com.daniel.data.services

import com.daniel.data.services.ItunesApiUrls.ItunesSearchApi.IBOOK_ENTITY
import com.daniel.data.services.ItunesApiUrls.ItunesSearchApi.SEARCH
import com.daniel.data.services.ItunesApiUrls.ItunesSearchApi.TERM

class ItunesApiUrls {

    object ItunesSearchApi {
        const val BASE_URL = "https://itunes.apple.com/"
        const val SEARCH = "search"
        const val TERM = "term"
        const val USER_INPUT = "{/term}"
        const val IBOOK_ENTITY = "entity"
    }

    object ItunesSearchApiUrls {
        fun buildRequestUrl(term: String): Map<String, String> {
            val data: LinkedHashMap<String, String> = LinkedHashMap()
            data[TERM] = term
            data[IBOOK_ENTITY] = "ibook"
            return data
        }

        const val SEARCH_BOOKS = ItunesSearchApi.BASE_URL.plus(SEARCH)
    }
}