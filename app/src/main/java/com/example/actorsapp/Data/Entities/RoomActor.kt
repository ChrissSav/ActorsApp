package com.example.actorsapp.Data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "actors")
data class RoomActor(

    @PrimaryKey
    val id: Int,

    val name: String,

    @ColumnInfo(name = "profile_path")

    val profilePath: String,

    val popularity: Float


) {
    override fun toString(): String {
        return "RoomActor(id=$id, name='$name', profilePath='$profilePath', popularity=$popularity')"
    }

}