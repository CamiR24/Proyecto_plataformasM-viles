package com.example.proyecto_plataformasmoviles.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_plataformasmoviles.data.repository.NotificacionesRepository
import com.example.proyecto_plataformasmoviles.data.repository.Notificacion
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class NotificacionesViewModel : ViewModel() {

    private val notificacionesRepository = NotificacionesRepository()

    // LiveData para las notificaciones
    private val _notificaciones = MutableLiveData<List<Notificacion>>()
    val notificaciones: LiveData<List<Notificacion>> get() = _notificaciones

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun cargarNotificaciones() {
        val usuarioId = FirebaseAuth.getInstance().currentUser?.uid
        if (usuarioId != null) {
            viewModelScope.launch {
                _isLoading.value = true
                val resultado = notificacionesRepository.obtenerNotificacionesDeUsuario(usuarioId)
                _isLoading.value = false
                if (resultado.isSuccess) {
                    _notificaciones.value = resultado.getOrNull() ?: emptyList()
                } else {
                    _error.value = "Error al obtener las notificaciones: ${resultado.exceptionOrNull()?.message}"
                    Log.e("NotificacionesVM", "Error: ${resultado.exceptionOrNull()}")
                }
            }
        } else {
            _error.value = "Usuario no autenticado"
        }
    }

    fun marcarNotificacionComoLeida(notificacionId: String) {
        viewModelScope.launch {

            val resultado = notificacionesRepository.marcarNotificacionComoLeida(notificacionId)
            if (resultado.isSuccess) {
                cargarNotificaciones()
            } else {
                _error.value = "Error al marcar como leída: ${resultado.exceptionOrNull()?.message}"
                Log.e("NotificacionesVM", "Error al marcar como leída: ${resultado.exceptionOrNull()}")
            }
        }
    }
}
