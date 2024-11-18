package com.example.proyecto_plataformasmoviles.data.model

import com.google.firebase.firestore.PropertyName

data class Perfil(
    @PropertyName("Antecedentes") val antecedentes: String = "",
    @PropertyName("Contraseña") val contraseña: String = "",
    @PropertyName("Cria") val cria: Boolean = false,
    @PropertyName("Edad_del_perro") val edad_del_perro: Int = 0,
    @PropertyName("Email") val email: String = "",
    @PropertyName("Entrenamiento") val entrenamiento: Boolean = false,
    @PropertyName("Nombre_del_perro") val nombre_del_perro: String = "",
    @PropertyName("Pareja") val pareja: Boolean = false,
    @PropertyName("Pedigree") val pedigree: Boolean = false,
    @PropertyName("Peso") val peso: Int = 0,
    @PropertyName("Raza_del_perro") val raza_del_perro: String = "",
    @PropertyName("Sexo") val sexo: String = "",
    @PropertyName("Tamaño") val tamaño: String = "",
    @PropertyName("Ubicacion") val ubicacion: String = "",
    @PropertyName("Usuario_id") val usuario_id: String = "",
)


