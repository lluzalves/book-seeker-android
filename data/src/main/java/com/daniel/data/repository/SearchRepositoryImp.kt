package com.daniel.data.repository

import com.daniel.commons.applyScheduler
import com.daniel.data.adapters.ItunesItemAdapter
import com.daniel.data.services.ItunesApiUrls
import com.daniel.data.services.ItunesSearchService
import com.daniel.domain.dto.ItunesItem
import com.daniel.domain.repository.SearchRepository
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchRepositoryImp : SearchRepository, KoinComponent {

    private val searchService: ItunesSearchService by inject()

    override fun searchItem(term: String, kind: String): Single<List<ItunesItem>> {
        when (kind) {
            "ibook" -> {
                return searchService.findBookItem(
                    ItunesApiUrls.ItunesSearchApiUrls.buildRequestUrl(
                        term = term
                    )
                ).applyScheduler()
                    .map { response ->
                        val books = ArrayList<ItunesItem>()
                        return@map response.result.mapTo(destination = books) { bookItem ->
                            ItunesItemAdapter.toItunesItem(bookItem)
                        }
                    }
            }
            else ->
                return searchService.findItem(term)
                    .applyScheduler()
                    .map { response ->
                        val items = ArrayList<ItunesItem>()
                        return@map response.result.mapTo(destination = items) { items ->
                            ItunesItemAdapter.toItunesItem(items)
                        }
                    }
        }
    }

    override fun retrieveListOf(): Single<List<ItunesItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
