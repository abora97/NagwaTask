package com.abora.nagwatask.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abora.nagwatask.di.scope.MyMapKey
import com.abora.nagwatask.ui.home.HomeViewModelImp
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MyViewModelModule {

    @Binds
    @IntoMap
    @MyMapKey(HomeViewModelImp::class)
    abstract fun bindHomeViewModelImp(loginViewModelImp : HomeViewModelImp) : ViewModel


//    @Binds
//    abstract fun bindViewModelFactory(factory: MyViewModelProviders): ViewModelProvider.Factory
}