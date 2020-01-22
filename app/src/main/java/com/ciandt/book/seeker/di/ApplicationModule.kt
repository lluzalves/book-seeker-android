package com.ciandt.book.seeker.di

import com.ciandt.book.seeker.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module(override = true) {
    viewModel { SearchViewModel() }
}
