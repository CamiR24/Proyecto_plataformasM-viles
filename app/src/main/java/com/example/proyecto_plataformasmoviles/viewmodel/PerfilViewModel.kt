package com.example.proyecto_plataformasmoviles.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_plataformasmoviles.data.model.Perfil
import com.example.proyecto_plataformasmoviles.data.repository.LikesRepository
import com.example.proyecto_plataformasmoviles.data.repository.PerfilesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(private val repository: PerfilesRepository, val likesRepository: LikesRepository) : ViewModel() {

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

    private val _perfilUsuario = MutableStateFlow<Perfil?>(null)
    val perfilUsuario: StateFlow<Perfil?> = _perfilUsuario

    private val _likesPorUsuario = MutableLiveData<List<String>>()
    val likesPorUsuario: LiveData<List<String>> get() = _likesPorUsuario

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
                Log.d("CARGAR_PERFIL_E", "Error al cargar el perro por edad")
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
                Log.d("CARGAR_PERFIL_U", "Error al cargar el perro por ubicación")
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
                Log.d("CARGAR_PERFIL_R", "Error al cargar el perro por raza")
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

    fun cargarPerfilUsuario(userId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.obtenerPerfilPorUsuario(userId)
            if (result.isSuccess) {
                _perfilUsuario.value = result.getOrNull()
            } else {
                _mensajeError.value = "Error al cargar el perfil del usuario"
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

    // Cargar "likes" de un usuario
    fun cargarLikesPorUsuario(usuarioId: String) {
        viewModelScope.launch {
            val result = likesRepository.obtenerLikesPorUsuario(usuarioId)
            if (result.isSuccess) {
                _likesPorUsuario.value = result.getOrDefault(emptyList())
            }
        }
    }

    // Dar o quitar "like"
    fun toggleLike(usuarioId: String, perfilId: String, isLiked: Boolean) {
        viewModelScope.launch {
            if (isLiked) {
                likesRepository.darLike(usuarioId, perfilId)
            } else {
                likesRepository.quitarLike(usuarioId, perfilId)
            }
            cargarLikesPorUsuario(usuarioId) // Refrescar los "likes"
        }
    }

    // Verificar si un perfil tiene "like" dado por el usuario
    suspend fun verificarLike(usuarioId: String, perfilId: String): Boolean {
        val result = likesRepository.verificarLike(usuarioId, perfilId)
        return result.getOrDefault(false)
    }

    fun contarLikes(perfilId: String): LiveData<Int> {
        val likesCount = MutableLiveData<Int>()
        viewModelScope.launch {
            val result = likesRepository.contarLikesPorPerfil(perfilId)
            likesCount.value = result.getOrDefault(0)
        }
        return likesCount
    }
}
