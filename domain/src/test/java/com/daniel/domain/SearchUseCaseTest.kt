package com.daniel.domain

import com.daniel.domain.dto.ItunesItem
import com.daniel.domain.usecase.search.SearchUseCase
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchUseCaseTest {

    @Test
    fun shouldRetrieveItunesItemsSuccesfully(){
        // given
        val itunesItem : ItunesItem = mock(ItunesItem::class.java)
        val itunesItemList : ArrayList<ItunesItem> = Mockito.spy(ArrayList())
        itunesItemList.add(itunesItem)
        val term = "test"

        val useCase : SearchUseCase = mock(SearchUseCase::class.java)
        val expectedResult : Single<List<ItunesItem>> = Single.just(itunesItemList)

        // when
        `when`(useCase.buildUseCaseSearchBook(term)).thenReturn(expectedResult)
        val resultList : List<ItunesItem> = useCase.buildUseCaseSearchBook(term).blockingGet()

        //then
        TestCase.assertEquals(itunesItemList, resultList)
    }
}