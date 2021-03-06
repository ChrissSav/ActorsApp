package com.example.actorsapp.UI.Details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.actorsapp.API.Models.ActorDetailsModel
import com.example.actorsapp.Data.ActorsDataBase
import com.example.actorsapp.Data.Entities.RoomActor
import com.example.actorsapp.repository.ApiRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val db: ActorsDataBase, private val endpoints: ApiRepository) :
    ViewModel() {

    private val _actor = MutableLiveData<ActorDetailsModel>()
    val actor: LiveData<ActorDetailsModel> = _actor

    private val _flag = MutableLiveData<Boolean>()
    val flag: LiveData<Boolean> = _flag


    private val _register = MutableLiveData<Boolean>()
    val register: LiveData<Boolean> = _register

    fun getPostFromAPiTest(id: Int) {
        viewModelScope.launch {

            val res = endpoints.getActorById(id)
            res?.let {
                _actor.value = it
            }

        }
    }

        fun deleteActorFromFav(actor: ActorDetailsModel) {
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

        fun registerActorToFav(actor: ActorDetailsModel) {
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