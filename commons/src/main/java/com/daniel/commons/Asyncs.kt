package com.daniel.commons

import com.daniel.commons.AppSchedulers.mainThreadScheduler
import com.daniel.commons.AppSchedulers.networkScheduler
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.applyScheduler(): Single<T> = this.compose { single ->
    single
        .subscribeOn(networkScheduler())
        .observeOn(mainThreadScheduler())
}

object AppSchedulers {

    fun networkScheduler(): Scheduler {
        return Schedulers.io()
    }

    fun mainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
