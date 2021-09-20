package com.abora.nagwatask.di.module

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings
import android.util.Log
import com.abora.nagwatask.retrofitApi.RetrofitApi
import com.abora.nagwatask.application.MyApp
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [ApplicationModule::class, SharedPrefrencesModule::class])
class NetworkModule {

    val url = "https://nagwa.free.beeceptor.com/"


    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun getOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor, sharedPreferences: SharedPreferences, context: Context): OkHttpClient {
        return OkHttpClient().newBuilder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor { chain ->
                    val request = chain.request()
                    val builder = request.newBuilder()
                            .addHeader("Accept-Language", MyApp.language)
                            .addHeader("Accept", "application/json")
                            .addHeader("Content-Type", "application/json")
                            .addHeader("device_type", "android")
                            .addHeader("Device-Name", android.os.Build.MODEL)
                            .addHeader("Device-OS-Version", android.os.Build.VERSION.RELEASE)
                            .addHeader("Device-UDID", Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID))
                            .addHeader("app_version", "1")
                            .addHeader("mobile_version", android.os.Build.VERSION.SDK_INT.toString())
                    if (sharedPreferences.contains("TOKEN")) {
                        Log.v("tokennn", "${sharedPreferences.getString("TOKEN", "")}            ${MyApp.language}")
                        builder.addHeader("Authorization", "Bearer ${sharedPreferences.getString("TOKEN", "")}")
                    }
                    val response = chain.proceed(builder.build())

                    if (response.code() == 401) {
                        // val intent = Intent(context,AuthenticationActivity::class.java)
                        //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        //context.startActivity(intent)
                    }

                    response
                }.build()
    }


    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun getRetrofitApi(retrofit: Retrofit): RetrofitApi {
        return retrofit.create(RetrofitApi::class.java)
    }

}