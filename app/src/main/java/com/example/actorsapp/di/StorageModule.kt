package com.example.actorsapp.di

import org.koin.dsl.module

val storageModule= module {
    single { provideDatabase(get()) }
}
