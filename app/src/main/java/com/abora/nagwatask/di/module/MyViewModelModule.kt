package com.abora.nagwatask.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abora.nagwatask.di.scope.MyMapKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MyViewModelModule {

//    @Binds
//    @IntoMap
//    @MyMapKey(LoginViewModelImp::class)
//    abstract fun bindLoginViewModelImp(loginViewModelImp : LoginViewModelImp) : ViewModel
//
//
//    @Binds
//    abstract fun bindViewModelFactory(factory: MyViewModelProviders): ViewModelProvider.Factory
}