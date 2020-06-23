package com.example.actorsapp.UI.Details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.actorsapp.API.ClientAPI
import com.example.actorsapp.API.Models.ActorDetails
import com.example.actorsapp.API.MoviesEndpoints
import com.example.actorsapp.Data.ActorsDataBase
import com.example.actorsapp.Data.DAO.ActorDAO
import com.example.actorsapp.Data.Entities.RoomActor
import kotlinx.coroutines.launch

class DetailsViewModel(private val db: ActorsDataBase,private val endpoints: MoviesEndpoints) : ViewModel() {

    private val _actor = MutableLiveData<ActorDetails>()
    val actor: LiveData<ActorDetails> = _actor

    private val _flag = MutableLiveData<Boolean>()
    val flag: LiveData<Boolean> = _flag


    private val _register = MutableLiveData<Boolean>()
    val register: LiveData<Boolean> = _register

    fun getPostFromAPiTest(id: Int) {
        viewModelScope.launch {
            //val request = ClientAPI.createService(MoviesEndpoints::class.java)

            val res = endpoints.getActorByIdTest(id = id)
            if (res.isSuccessful) {
                _actor.value = res.body()
            } else {
                Log.i("testtest", res.message())

            }
        }
    }

    fun deleteActorFromFav(actor: ActorDetails) {
        viewModelScope.launch {
            db.currentActorDao().deleteActor(
                RoomActor(
                    actor.id, actor.name, actor.profilePath
                    , actor.popularity
                )
            )
            _flag.value = true

        }
    }

    fun registerActorToFav(actor: ActorDetails) {
        viewModelScope.launch {
            db.currentActorDao().insertOrUpdateActor(
                RoomActor(
                    actor.id, actor.name, actor.profilePath
                    , actor.popularity
                )
            )

            _register.value = true

        }
    }


}