package com.example.actorsapp.API.Models

import android.security.identity.AccessControlProfile
import com.google.gson.annotations.SerializedName

data class ActorModel (
    val id:Int,
    val name:String,
    @SerializedName("profile_path")
    val profilePath: String,
    val popularity: Float
    
)