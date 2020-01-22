package com.daniel.domain.repository

import com.daniel.domain.dto.ItunesItem
import io.reactivex.Single

interface SearchRepository : Repository<ItunesItem> {
    fun searchItem(term: String, kind : String) : Single<List<ItunesItem>>
}