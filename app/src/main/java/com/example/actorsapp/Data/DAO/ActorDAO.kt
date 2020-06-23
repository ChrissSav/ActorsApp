package com.example.actorsapp.Data.DAO

import androidx.room.*
import com.example.actorsapp.Data.Entities.RoomActor


@Dao
interface ActorDAO {


    @Query("select * from actors")
    suspend fun getActors(): List<RoomActor>

    @Query("select * from actors where id = :id")
    suspend fun getActorById(id :Int): RoomActor

    @Delete
    suspend fun deleteActor(actor :RoomActor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateActor(actor: RoomActor): Long

}