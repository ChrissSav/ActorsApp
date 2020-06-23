package com.example.actorsapp.UI.Favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.actorsapp.Data.ActorsDataBase
import com.example.actorsapp.Data.Entities.RoomActor
import kotlinx.coroutines.launch

class FavoritesViewModel(private val db: ActorsDataBase) : ViewModel() {
    private val _actorList = MutableLiveData<List<RoomActor>>()
    val actorList: LiveData<List<RoomActor>> = _actorList




    fun getPostFromDataBase() {
        viewModelScope.launch {

            _actorList.value = db.currentActorDao().getActors()
            // =request.getActorsTest(page = page).*/
        }
    }
}