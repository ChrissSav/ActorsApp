package com.example.actorsapp.UI.Actors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.actorsapp.API.Models.ActorModel
import com.example.actorsapp.Data.ActorsDataBase
import com.example.actorsapp.Data.Entities.RoomActor
import com.example.actorsapp.repository.ApiRepository
import kotlinx.coroutines.launch

class ActorsViewModel
    (
    private val db: ActorsDataBase,
    private val endpoints: ApiRepository
) :
    ViewModel() {


    private var page: Int = 0

    private val _actorsList = MutableLiveData<ArrayList<Pair<ActorModel, Boolean>>>()
    val actorsList: LiveData<ArrayList<Pair<ActorModel, Boolean>>> = _actorsList

    private lateinit var dataList: List<RoomActor>


    fun getActorsFromApi() {
        viewModelScope.launch {

            var finalList: ArrayList<Pair<ActorModel, Boolean>> = ArrayList()
            dataList = db.currentActorDao().getActors()

            page++
            val apiList = endpoints.getActors(page = page)

            if (apiList.isNotEmpty()) {

                for (items in apiList) {

                    if (checkActor(items))
                        finalList.add(Pair(items, true))
                    else
                        finalList.add(Pair(items, false))
                }
                _actorsList.value = finalList
            }
        }
    }

    private fun checkActor(actor: ActorModel): Boolean {
        for (item in dataList) {
            if (item.id == actor.id)
                return true
        }
        return false
    }

}