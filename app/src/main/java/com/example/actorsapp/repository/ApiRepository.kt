package com.example.actorsapp.repository

import com.example.actorsapp.API.Api
import com.example.actorsapp.API.Models.ActorDetailsModel
import com.example.actorsapp.API.Models.ActorModel

interface ApiRepository {
    suspend fun getActors(page: Int): ArrayList<ActorModel>
    suspend fun getActorById(id: Int): ActorDetailsModel?
}


class ApiRepositoryImpl(
    private val middlewareApi: Api
) : ApiRepository {
    override suspend fun getActors(page: Int): ArrayList<ActorModel> {

        val res = middlewareApi.getActorsTest(page = page)
        if (res.isSuccessful) {
            return res.body()!!.results
        }
        return ArrayList()

    }

    override suspend fun getActorById(id: Int): ActorDetailsModel? {
        val res = middlewareApi.getActorByIdTest(id = id)
        if (res.isSuccessful) {
            return res.body()!!
        }
        return null
    }

}
