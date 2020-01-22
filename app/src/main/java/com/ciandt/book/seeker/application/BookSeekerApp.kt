package com.ciandt.book.seeker.application

import android.app.Application
import com.ciandt.book.seeker.di.applicationModule
import com.daniel.data.di.dataModule
import com.daniel.data.di.networkModule
import com.daniel.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BookSeekerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BookSeekerApp)
            modules(
                listOf(
                    networkModule,
                    dataModule,
                    domainModule,
                    applicationModule
                )
            )
        }
    }
}