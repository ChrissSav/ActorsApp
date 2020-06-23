package com.example.actorsapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.actorsapp.Data.ActorsDataBase

class ApplicationClass : Application() {

    lateinit var db : ActorsDataBase
    /*companion object {
        var ctx: Context? = null
    }*/

    override fun onCreate() {
        super.onCreate()
        //ctx = applicationContext
        db = ActorsDataBase(this)

    }
}