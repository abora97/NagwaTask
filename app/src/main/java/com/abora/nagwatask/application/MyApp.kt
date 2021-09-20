package com.abora.nagwatask.application

import android.content.Context
import android.content.SharedPreferences
import com.abora.nagwatask.di.component.AppComponent
import com.abora.nagwatask.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject


class MyApp : DaggerApplication() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    companion object {
        var language : String = ""
    }

    override fun onCreate() {
        super.onCreate()

        language = if (sharedPreferences.getString("lang",resources.configuration.locale.language) == "en") {
            "en"
        } else {
            "ar"
        }


    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)


    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        var myComponent: AppComponent = DaggerAppComponent.builder().builder(this).build()
        myComponent.inject(this)
        return myComponent
    }


}