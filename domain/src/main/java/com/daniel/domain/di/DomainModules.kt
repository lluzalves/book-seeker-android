package com.daniel.domain.di

import com.daniel.domain.usecase.search.SearchUseCase
import org.koin.dsl.module

val domainModule = module(override = true) {
    factory { SearchUseCase() }
}