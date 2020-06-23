package com.example.actorsapp.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.actorsapp.Data.DAO.ActorDAO
import com.example.actorsapp.Data.Entities.RoomActor


@Database(
    entities = [RoomActor::class],
    version = 1
)
abstract class ActorsDataBase : RoomDatabase() {

    abstract fun currentActorDao(): ActorDAO



    companion object {
        @Volatile
        private var INSTANCE: ActorsDataBase? = null

        fun getInstance(context: Context): ActorsDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ActorsDataBase::class.java,
                    "movies.db"
                )
                    //.addMigrations(*DatabaseMigrations.MIGRATIONS)
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }


}