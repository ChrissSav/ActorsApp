package com.example.actorsapp.API.Models

import android.security.identity.AccessControlProfile
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ActorModel (
    val id:Int,
    val name:String,
    @Json(name ="profile_path")
    val profilePath: String,
    val popularity: Float
    
)