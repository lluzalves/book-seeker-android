package com.daniel.data.di

import com.daniel.commons.BuildConfig
import com.daniel.data.network.NetworkFactory
import com.daniel.data.repository.SearchRepositoryImp
import com.daniel.data.services.ItunesApiUrls
import com.daniel.data.services.ItunesSearchService
import com.daniel.domain.repository.SearchRepository
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module(override = true) {
    factory<ItunesSearchService> {
        NetworkFactory().webService(ItunesApiUrls.ItunesSearchApi.BASE_URL).newBuilder().build()
            .create(ItunesSearchService::class.java)
    }
}

val dataModule = module(override = true) {
    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BASIC
            }
        }
    }
    single { return@single SearchRepositoryImp() as SearchRepository }
    single { GsonConverterFactory.create(GsonBuilder().create()) }
}