package com.abora.nagwatask.di.module


import com.abora.nagwatask.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityContributeModule {

    @ContributesAndroidInjector
    abstract fun getMainActivity(): MainActivity



}