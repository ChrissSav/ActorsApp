package com.example.actorsapp.UI.Actors

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.actorsapp.API.ClientAPI
import com.example.actorsapp.API.Models.ActorModel
import com.example.actorsapp.API.MoviesEndpoints
import com.example.actorsapp.Data.DAO.ActorDAO
import com.example.actorsapp.Data.Entities.RoomActor
import kotlinx.coroutines.launch

class ActorsViewModel : ViewModel() {
    private val _actorsList = MutableLiveData<ArrayList<Pair<ActorModel, Boolean>>>()
    private lateinit var dataList: List<RoomActor>

    val postList: LiveData<ArrayList<Pair<ActorModel, Boolean>>> = _actorsList


    fun getActorsFromApi(page: Int, actorsDataBase: ActorDAO) {
        Log.i("estila", "page: $page")
        viewModelScope.launch {
            var final: ArrayList<Pair<ActorModel, Boolean>> = ArrayList()
            dataList = actorsDataBase.getActors()

            val request = ClientAPI.createService(MoviesEndpoints::class.java)

            val res = request.getActorsTest(page = page)
            if (res.isSuccessful) {
                val apiList = res.body()?.results
                if (apiList?.isNotEmpty()!!) {
                    for (items in apiList!!) {
                        if (test(items))
                            final.add(Pair(items, true))
                        else
                            final.add(Pair(items, false))
                    }
                    _actorsList.value = final
                }
                // _actorsList.value = res.body()?.results

            } else {


            }
        }
    }

    fun test(actor: ActorModel): Boolean {
        for (item in dataList) {
            if (item.id == actor.id)
                return true
        }
        return false
    }

}