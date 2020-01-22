package com.daniel.domain.usecase.search

import com.daniel.domain.dto.ItunesItem
import com.daniel.domain.repository.SearchRepository
import com.daniel.domain.usecase.UseCase
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchUseCase : UseCase<ItunesItem>(), KoinComponent {

    private val searchRepository: SearchRepository by inject()
    val BOOKS = "ibook"


    fun buildUseCaseSearchBook(value: String): Single<List<ItunesItem>> {
        return searchRepository.searchItem(value, BOOKS)
    }

    override fun buildUseCase(value: String): Single<List<ItunesItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}