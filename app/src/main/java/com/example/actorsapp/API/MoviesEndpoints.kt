package com.example.actorsapp.API

import com.example.actorsapp.API.Models.ActorDetails
import com.example.actorsapp.API.Models.ResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesEndpoints {


  /*  @GET("person/popular")
    fun getActors(
        @Query("api_key") action: String,
        @Query("page") page: Int
    ): Call<ResponseModel>

    @GET("person/{id}")
    fun getActorById(
        @Path("id") id: Int,
        @Query("api_key") action: String
    ): Call<ActorDetails>*/

    @GET("person/popular")
    suspend fun getActorsTest(
        @Query("api_key") api_key: String = "665fceecdbf2bdcf98531e035c2fb63d",
        @Query("page") page: Int
    ): Response<ResponseModel>




    @GET("person/{id}")
    suspend fun getActorByIdTest(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = "665fceecdbf2bdcf98531e035c2fb63d"
    ): Response<ActorDetails>
}