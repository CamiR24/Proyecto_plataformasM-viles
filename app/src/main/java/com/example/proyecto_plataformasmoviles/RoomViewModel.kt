package com.example.proyecto_plataformasmoviles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_plataformasmoviles.Injection
import com.example.proyecto_plataformasmoviles.Result.*
import com.example.proyecto_plataformasmoviles.Room
import com.example.proyecto_plataformasmoviles.RoomRepository
import kotlinx.coroutines.launch
class RoomViewModel : ViewModel() {
    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: LiveData<List<Room>> get() = _rooms
    private val roomRepository: RoomRepository
    init {
        roomRepository = RoomRepository(Injection.instance())
        loadRooms()
    }
    fun createRoom(name: String) {
        viewModelScope.launch {
            roomRepository.createRoom(name)
        }
    }
    fun loadRooms() {
        viewModelScope.launch {
            when (val result = roomRepository.getRooms()) {
                is Success -> _rooms.value = result.data
                is Error -> {
                }
            }
        }
    }
}