package com.abora.nagwatask.di.component

import android.app.Application
import com.abora.nagwatask.application.MyApp
import com.abora.nagwatask.di.module.*

import dagger.BindsInstance
import dagger.Component

import dagger.android.AndroidInjector

import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, NetworkModule::class, SharedPrefrencesModule::class, ActivityContributeModule::class, MyViewModelModule::class])
interface AppComponent : AndroidInjector<MyApp> {


    @Component.Builder
    interface Builder{

        @BindsInstance
        fun builder(application: Application): Builder


        fun build(): AppComponent

    }


    fun inject(application: Application)

}