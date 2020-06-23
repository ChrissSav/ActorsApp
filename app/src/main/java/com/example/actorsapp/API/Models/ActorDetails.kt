package com.example.actorsapp.API.Models

import com.google.gson.annotations.SerializedName

data class ActorDetails (
    val id:Int,
    val name:String,
    @SerializedName("profile_path")
    val profilePath: String,
    val popularity: Float,
    val biography: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String

)