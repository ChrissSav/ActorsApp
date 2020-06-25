package com.example.actorsapp.API.Models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ActorDetailsModel (
    val id:Int,
    val name:String,
    @Json(name = "profile_path")
    val profilePath: String,
    val popularity: Float,
    val biography: String,
    @Json(name = "place_of_birth")
    val placeOfBirth: String

)