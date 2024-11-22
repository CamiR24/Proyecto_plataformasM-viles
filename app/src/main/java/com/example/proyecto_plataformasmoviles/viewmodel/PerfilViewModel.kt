package com.example.proyecto_plataformasmoviles.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.proyecto_plataformasmoviles.data.model.Perfil
import com.example.proyecto_plataformasmoviles.data.repository.LikesRepository
import com.example.proyecto_plataformasmoviles.data.repository.MatchesRepository
import com.example.proyecto_plataformasmoviles.data.repository.NotificacionesRepository
import com.example.proyecto_plataformasmoviles.data.repository.PerfilesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(private val repository: PerfilesRepository, val likesRepository: LikesRepository, val matchesRepository: MatchesRepository) : ViewModel() {

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

    private val _recomendacionesPorTamaño = MutableStateFlow<List<Perfil>>(emptyList())
    val recomendacionesPorTamaño: StateFlow<List<Perfil>> = _recomendacionesPorTamaño

    private val _recomendacionesPorPeso = MutableStateFlow<List<Perfil>>(emptyList())
    val recomendacionesPorPeso: StateFlow<List<Perfil>> = _recomendacionesPorPeso

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _perfilUsuario = MutableStateFlow<Perfil?>(null)
    val perfilUsuario: StateFlow<Perfil?> = _perfilUsuario

    private val _likesPorUsuario = MutableLiveData<List<String>>()
    val likesPorUsuario: LiveData<List<String>> get() = _likesPorUsuario

    private val _matches = MutableStateFlow<List<Perfil>>(emptyList())
    val matches: StateFlow<List<Perfil>> = _matches

    fun crearPerfil(perfil: Perfil, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.crearPerfil(perfil)
            if (result.isSuccess) {
                onSuccess() // Llama al callback de éxito
            } else {
                val errorMessage = result.exceptionOrNull()?.message ?: "Error desconocido"
                onError(errorMessage) // Llama al callback de error
                _mensajeError.value = errorMessage
            }
            _isLoading.value = false
        }
    }

    fun obtenerNombrePorUsuario(usuarioId: String, onResult: (String?) -> Unit) {
        viewModelScope.launch {
            val result = repository.obtenerPerfilPorUsuario(usuarioId)
            if (result.isSuccess) {
                val nombre = result.getOrNull()?.nombre_del_perro
                onResult(nombre)
            } else {
                onResult(null) // Retorna null si hay un error
            }
        }
    }

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

    fun cargarRecomendacionesPorTamaño(tamaño: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _recomendacionesPorTamaño.value = emptyList()
            val result = repository.obtenerPerfilesPorTamaño(tamaño)
            if (result.isSuccess) {
                _recomendacionesPorTamaño.value = result.getOrDefault(emptyList())
            } else {
                Log.d("CARGAR_PERFIL_T", "Error al cargar el perro por tamaño")
            }
            _isLoading.value = false
        }
    }

    fun cargarRecomendacionesPorPeso(peso: Int) {
        val rango = Pair(peso - 3, peso + 3) // Rango de ±3 kg
        viewModelScope.launch {
            _isLoading.value = true
            _recomendacionesPorPeso.value = emptyList()
            val result = repository.obtenerPerfilesPorRangoDePeso(rango.first, rango.second)
            if (result.isSuccess) {
                _recomendacionesPorPeso.value = result.getOrDefault(emptyList())
            } else {
                Log.d("CARGAR_PERFIL_P", "Error al cargar el perro por peso")
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

    // Cargar los matches del usuario actual
    fun cargarMatchesDeUsuario(usuarioId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = matchesRepository.obtenerMatchesDeUsuario(usuarioId)
            if (result.isSuccess) {
                val matchedUserIds = result.getOrDefault(emptyList())
                val matchedProfiles = mutableListOf<Perfil>()

                // Cargar los perfiles de cada usuario con el que se hizo match
                for (matchedUserId in matchedUserIds) {
                    val perfilResult = repository.obtenerPerfilPorUsuario(matchedUserId)
                    perfilResult.getOrNull()?.let { matchedProfiles.add(it) }
                }

                _matches.value = matchedProfiles
            } else {
                _mensajeError.value = "Error al cargar los matches: ${result.exceptionOrNull()?.message}"
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
    fun toggleLike(
        usuarioId: String,
        perfilId: String,
        isLiked: Boolean,
        matchesRepository: MatchesRepository,
        notificacionesRepository: NotificacionesRepository,
        navController: NavController
    ) {
        viewModelScope.launch {
            if (isLiked) {
                val result = likesRepository.darLike(
                    usuarioId,
                    perfilId,
                    matchesRepository,
                    notificacionesRepository
                ) { id1, id2 ->
                    // Navega al DogMatchScreen con los IDs
                    navController.navigate("Match/$id1/$id2")
                }
                if (result.isFailure) {
                    _mensajeError.value = "Error al dar like: ${result.exceptionOrNull()?.message}"
                }
            } else {
                val result = likesRepository.quitarLike(usuarioId, perfilId)
                if (result.isSuccess) {
                    // Verificar si había un match y eliminarlo si existe
                    matchesRepository.eliminarMatch(usuarioId, perfilId)

                    _matches.value = _matches.value.filter { it.usuario_id != perfilId }
                } else {
                    _mensajeError.value = "Error al quitar like: ${result.exceptionOrNull()?.message}"
                }
            }
            cargarLikesPorUsuario(usuarioId) // Refrescar los likes
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
