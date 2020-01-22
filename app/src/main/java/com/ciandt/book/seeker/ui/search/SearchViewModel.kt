package com.ciandt.book.seeker.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciandt.book.seeker.ui.search.SearchDataState.*
import com.daniel.domain.dto.ItunesItem
import com.daniel.domain.usecase.search.SearchUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import org.koin.core.KoinComponent
import org.koin.core.inject


class SearchViewModel : ViewModel(), KoinComponent {

    private val _searchState = MutableLiveData<SearchDataState>()
    val searchState: LiveData<SearchDataState>
        get() = _searchState

    private val searchDataUseCase: SearchUseCase by inject()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onStart(){
        emmitState(State.INITIAL,null,null)
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }

    fun restoreState() {
        emmitState(state = State.COMPLETED, data = searchState.value?.data, error = null)

    }

    fun searchTerm(term: String) {
        emmitState(State.LOADING, null, null)
        compositeDisposable.addAll(searchDataUseCase.buildUseCaseSearchBook(term)
            .subscribeBy(
                onSuccess = { items ->
                    emmitState(state = State.COMPLETED, data = items, error = null)
                },
                onError = {
                    emmitState(
                        state = State.FAILED,
                        data = null,
                        error = it.localizedMessage
                    )
                }
            ))
    }

    private fun emmitState(state: State, data: List<ItunesItem>?, error: String?) {
        when (state) {
            State.INITIAL ->{
                val searchData =
                    SearchDataState(
                        state = State.INITIAL,
                        data = data,
                        showLoadingProgression = false,
                        errorMessage = null
                    )
                _searchState.value = searchData
            }
            State.COMPLETED -> {
                val searchData =
                    SearchDataState(
                        state = State.COMPLETED,
                        data = data,
                        showLoadingProgression = false,
                        errorMessage = null
                    )
                _searchState.value = searchData
            }
            State.LOADING -> {
                val searchData =
                    SearchDataState(
                        state = State.LOADING,
                        data = data,
                        showLoadingProgression = true,
                        errorMessage = null
                    )
                _searchState.value = searchData
            }
            State.FAILED -> {
                val searchData =
                    SearchDataState(
                        state = State.FAILED,
                        data = data,
                        showLoadingProgression = false,
                        errorMessage = error
                    )
                _searchState.value = searchData
            }
        }
    }

}

data class SearchDataState(
    var state: State,
    var data: List<ItunesItem>?,
    var showLoadingProgression: Boolean = false,
    var errorMessage: String? = null
) {

    enum class State {
        COMPLETED,
        LOADING,
        FAILED,
        INITIAL
    }
}