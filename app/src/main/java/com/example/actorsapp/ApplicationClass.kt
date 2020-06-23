package com.example.actorsapp

import android.app.Application
import com.example.actorsapp.di.*
import org.koin.core.context.startKoin

class ApplicationClass : Application() {


    override fun onCreate() {
        super.onCreate()


        startKoin {
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