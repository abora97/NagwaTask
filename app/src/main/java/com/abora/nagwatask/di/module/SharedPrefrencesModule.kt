package com.abora.nagwatask.di.module

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.abora.nagwatask.di.module.ApplicationModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ApplicationModule::class])
class SharedPrefrencesModule {

    @Singleton
    @Provides
    fun getSharedPrefrences(context: Context): SharedPreferences {
        return context.getSharedPreferences("nagwatask",MODE_PRIVATE)
    }


}