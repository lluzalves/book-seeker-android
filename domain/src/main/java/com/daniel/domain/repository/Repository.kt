package com.daniel.domain.repository

import io.reactivex.Single
import org.koin.core.KoinComponent

interface Repository<T> : KoinComponent{
    fun retrieveListOf(): Single<List<T>>
}