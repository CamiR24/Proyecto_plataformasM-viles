package com.example.proyecto_plataformasmoviles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.example.proyecto_plataformasmoviles.Injection
import com.example.proyecto_plataformasmoviles.Message
import com.example.proyecto_plataformasmoviles.MessageRepository
import com.example.proyecto_plataformasmoviles.Result.*
import com.example.proyecto_plataformasmoviles.User
import com.example.proyecto_plataformasmoviles.UserRepository
import kotlinx.coroutines.launch
class MessageViewModel : ViewModel() {
    private val messageRepository: MessageRepository
    private val userRepository: UserRepository
    init {
        messageRepository = MessageRepository(Injection.instance())
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
        loadCurrentUser()
    }
    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> get() = _messages
    private val _roomId = MutableLiveData<String>()
    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User> get() = _currentUser
    fun setRoomId(roomId: String) {
        _roomId.value = roomId
        loadMessages()
    }
    fun sendMessage(text: String) {
        if (_currentUser.value != null) {
            val message = Message(
                senderFirstName = _currentUser.value!!.firstName,
                senderId = _currentUser.value!!.email,
                text = text
            )
            viewModelScope.launch {
                when (messageRepository.sendMessage(_roomId.value.toString(), message)) {
                    is Success -> Unit
                    is Error -> {
                    }
                }
            }
        }
    }
    fun loadMessages() {
        viewModelScope.launch {
            if (_roomId != null) {
                messageRepository.getChatMessages(_roomId.value.toString())
                    .collect { _messages.value = it }
            }
        }
    }
    private fun loadCurrentUser() {
        viewModelScope.launch {
            when (val result = userRepository.getCurrentUser()) {
                is Success -> _currentUser.value = result.data
                is Error -> {
                }
            }
        }
    }
}