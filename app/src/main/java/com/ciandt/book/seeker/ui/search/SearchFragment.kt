package com.ciandt.book.seeker.ui.search

import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.provider.HistorySuggestionProvider
import com.ciandt.book.seeker.ui.ItunesItemAdapter
import com.ciandt.book.seeker.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var itemsAdapter: ItunesItemAdapter
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        app_name.text = getString(R.string.search)
        searchViewModel.searchState.observe(this, Observer { searchDataState ->
            val dataState = searchDataState ?: return@Observer
            onDataStateHandleViewState(dataState)
        })
        if (searchViewModel.searchState.value?.state == SearchDataState.State.COMPLETED) {
            searchViewModel.restoreState()
        } else {
            searchViewModel.onStart()
        }
        setupSearchView()
        setupUiItems()
        retainInstance = true
    }

    override fun onDestroy() {
        super.onDestroy()
        searchViewModel.onDestroy()
    }

    private fun setupUiItems() {
        staggeredGridLayoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        itemsRecycler.layoutManager = staggeredGridLayoutManager
        staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        registerForContextMenu(itemsRecycler)
    }

    private fun setupSearchView() {
        val searchableInfo = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchableInfo.getSearchableInfo(activity!!.componentName))
        searchView.isQueryRefinementEnabled = true
        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                onSearchViewSuggestionInteraction(position)
                return true
            }

            override fun onSuggestionClick(position: Int): Boolean {
                onSearchViewSuggestionInteraction(position)
                return true
            }

        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(term: String): Boolean {
                searchViewModel.searchTerm(term)
                saveSuggestion(query = term)
                searchView.clearFocus()
                searchView.onActionViewCollapsed()
                return true
            }

            override fun onQueryTextChange(term: String): Boolean {
                return false
            }
        })
    }

    private fun onSearchViewSuggestionInteraction(position: Int) {
        (activity as MainActivity).hideKeyboard()
        val cursor: Cursor = searchView.suggestionsAdapter.cursor
        cursor.moveToPosition(position)
        val term = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
        searchViewModel.searchTerm(term)
    }

    private fun saveSuggestion(query: String) {
        SearchRecentSuggestions(
            context,
            HistorySuggestionProvider.AUTHORITY,
            HistorySuggestionProvider.MODE
        ).saveRecentQuery(query, null)
    }

    private fun onDataStateHandleViewState(dataState: SearchDataState) {
        if (dataState.state == SearchDataState.State.COMPLETED &&
            dataState.data != null
        ) {
            dataState.data?.let { itunesItems ->
                itemsRecycler.visibility = View.VISIBLE
                itemsAdapter =
                    ItunesItemAdapter(itunesItems)
                itemsRecycler.adapter = itemsAdapter
            }

        }
        if (dataState.state == SearchDataState.State.FAILED) {
            view?.let { Snackbar.make(it,getString(R.string.error,dataState.errorMessage),Snackbar.LENGTH_LONG).show() }
        }
        progressLayout.visibility = if (dataState.showLoadingProgression) {
            View.VISIBLE
        } else {
            View.GONE
        }
        appName.visibility =
            if (dataState.state == SearchDataState.State.LOADING || dataState.state == SearchDataState.State.COMPLETED) {
                View.GONE
            } else {
                View.VISIBLE
            }
        appIcon.visibility =
            if (dataState.state == SearchDataState.State.LOADING || dataState.state == SearchDataState.State.COMPLETED) {
                View.GONE
            } else {
                View.VISIBLE
            }
        searchView.visibility = if (dataState.state == SearchDataState.State.INITIAL) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }


}