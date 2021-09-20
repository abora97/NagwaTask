package com.abora.nagwatask.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun getContext(app : Application): Context {
        return app.applicationContext
    }

}