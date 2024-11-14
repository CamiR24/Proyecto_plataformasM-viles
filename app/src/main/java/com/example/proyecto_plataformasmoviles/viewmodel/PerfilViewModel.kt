package com.example.proyecto_plataformasmoviles.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_plataformasmoviles.data.model.Perfil
import com.example.proyecto_plataformasmoviles.data.repository.PerfilesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(private val repository: PerfilesRepository) : ViewModel() {

    private val _perfiles = MutableStateFlow<List<Perfil>>(emptyList())
    val perfiles: StateFlow<List<Perfil>> = _perfiles

    private val _mensajeError = MutableStateFlow<String?>(null)
    val mensajeError: StateFlow<String?> = _mensajeError

    private val _recomendacionesPorEdad = MutableStateFlow<List<Perfil>>(emptyList())
    val recomendacionesPorEdad: StateFlow<List<Perfil>> = _recomendacionesPorEdad

    private val _recomendacionesPorUbicacion = MutableStateFlow<List<Perfil>>(emptyList())
    val recomendacionesPorUbicacion: StateFlow<List<Perfil>> = _recomendacionesPorUbicacion

    private val _recomendacionesPorRaza = MutableStateFlow<List<Perfil>>(emptyList())
    val recomendacionesPorRaza: StateFlow<List<Perfil>> = _recomendacionesPorRaza

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun cargarRecomendacionesPorEdad(edad: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _recomendacionesPorEdad.value = emptyList()
            val result = repository.obtenerPerfilesPorEdad(edad)
            if (result.isSuccess) {
                val perfilesEncontrados = result.getOrDefault(emptyList())
                Log.d("CARGAR_PERFIL_E", "Cantidad de perfiles encontrados: ${perfilesEncontrados.size}")

                // Imprimir detalles de cada perfil encontrado
                perfilesEncontrados.forEach { perfil ->
                    Log.d("CARGAR_PERFIL_E", "Perfil encontrado: ${perfil.nombre_del_perro}, Edad: ${perfil.edad_del_perro}")
                }
                _recomendacionesPorEdad.value = result.getOrDefault(emptyList())
            } else {
                _mensajeError.value = "Error al cargar recomendaciones por edad"
            }
            _isLoading.value = false
        }
    }

    fun cargarRecomendacionesPorUbicacion(ubicacion: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _recomendacionesPorUbicacion.value = emptyList()
            val result = repository.obtenerPerfilesPorUbicacion(ubicacion)
            if (result.isSuccess) {
                val perfilesEncontrados = result.getOrDefault(emptyList())
                Log.d("CARGAR_PERFIL_U", "Cantidad de perfiles encontrados: ${perfilesEncontrados.size}")

                // Imprimir detalles de cada perfil encontrado
                perfilesEncontrados.forEach { perfil ->
                    Log.d("CARGAR_PERFIL_U", "Perfil encontrado: ${perfil.nombre_del_perro}, Ubicación: ${perfil.ubicacion}")
                }
                _recomendacionesPorUbicacion.value = result.getOrDefault(emptyList())
            } else {
                _mensajeError.value = "Error al cargar recomendaciones por ubicación"
            }
            _isLoading.value = false
        }
    }

    fun cargarRecomendacionesPorRaza(raza: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _recomendacionesPorRaza.value = emptyList()
            val result = repository.obtenerPerfilesPorRaza(raza)
            if (result.isSuccess) {
                val perfilesEncontrados = result.getOrDefault(emptyList())
                Log.d("CARGAR_PERFIL_R", "Cantidad de perfiles encontrados: ${perfilesEncontrados.size}")

                // Imprimir detalles de cada perfil encontrado
                perfilesEncontrados.forEach { perfil ->
                    Log.d("CARGAR_PERFIL_R", "Perfil encontrado: ${perfil.nombre_del_perro}, Raza: ${perfil.raza_del_perro}")
                }
                _recomendacionesPorRaza.value = result.getOrDefault(emptyList())
            } else {
                _mensajeError.value = "Error al cargar recomendaciones por raza"
            }
            _isLoading.value = false
        }
    }

    fun cargarPerfiles() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.obtenerPerfiles()
            if (result.isSuccess) {
                _perfiles.value = result.getOrDefault(emptyList())
            } else {
                _mensajeError.value = "Error al cargar perfiles: ${result.exceptionOrNull()?.message}"
            }
            _isLoading.value = false
        }
    }

    fun agregarPerfil(perfil: Perfil) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.crearPerfil(perfil)
            if (result.isSuccess) {
                cargarPerfiles() // Refresca la lista de perfiles después de agregar
            } else {
                _mensajeError.value = "Error al agregar perfil: ${result.exceptionOrNull()?.message}"
            }
            _isLoading.value = false
        }
    }

    fun actualizarPerfil(id: String, perfil: Perfil) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.actualizarPerfil(id, perfil)
            if (result.isSuccess) {
                cargarPerfiles() // Refresca la lista de perfiles después de actualizar
            } else {
                _mensajeError.value = "Error al actualizar perfil: ${result.exceptionOrNull()?.message}"
            }
            _isLoading.value = false
        }
    }

    fun eliminarPerfil(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.eliminarPerfil(id)
            if (result.isSuccess) {
                cargarPerfiles() // Refresca la lista de perfiles después de eliminar
            } else {
                _mensajeError.value = "Error al eliminar perfil: ${result.exceptionOrNull()?.message}"
            }
            _isLoading.value = false
        }
    }
}
