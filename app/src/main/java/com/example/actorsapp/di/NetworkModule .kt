package com.example.actorsapp.di

import com.example.actorsapp.API.Api
import com.example.actorsapp.BuildConfig
import com.example.actorsapp.repository.ApiRepository
import com.example.actorsapp.repository.ApiRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {


    single { provideRetrofit<Api>(get()) }
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { ApiRepositoryImpl(get()) as ApiRepository }


}


fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
    if (BuildConfig.DEBUG) {
        okHttpClient.addNetworkInterceptor(httpLoggingInterceptor)
    }
    return okHttpClient.build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

inline fun <reified T> provideRetrofit(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
    return retrofit.create(T::class.java)
}