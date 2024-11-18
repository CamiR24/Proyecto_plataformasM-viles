package com.example.proyecto_plataformasmoviles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_plataformasmoviles.data.repository.LikesRepository
import com.example.proyecto_plataformasmoviles.data.repository.PerfilesRepository

class PerfilViewModelFactory(
    private val perfilesRepository: PerfilesRepository,
    private val likesRepository: LikesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PerfilViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PerfilViewModel(perfilesRepository, likesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

