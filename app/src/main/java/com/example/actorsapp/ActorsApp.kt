package com.example.actorsapp

import android.app.Application
import com.example.actorsapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ActorsApp : Application() {


    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@ActorsApp)
            modules(
                myModules,
                storageModule,
                viewModelModuleFav,
                viewModelModuleActor,
                viewModelModuleDetails
            )
        }
    }


}