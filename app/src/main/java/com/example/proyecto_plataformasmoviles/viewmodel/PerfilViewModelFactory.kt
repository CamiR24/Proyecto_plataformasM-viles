package com.example.proyecto_plataformasmoviles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_plataformasmoviles.data.repository.PerfilesRepository

class PerfilViewModelFactory(
    private val repository: PerfilesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PerfilViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PerfilViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
