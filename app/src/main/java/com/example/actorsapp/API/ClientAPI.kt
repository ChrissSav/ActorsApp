package com.example.actorsapp.API

import com.example.actorsapp.Data.ActorsDataBase
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class ClientAPI {
    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        /*fun createService(serviceClass: Class<ClientAPI>): ClientAPI {
            return retrofit.create(serviceClass)
        }*/
        fun<T> createService(service: Class<T>): T{
            return retrofit.create(service)
        }
    }

}