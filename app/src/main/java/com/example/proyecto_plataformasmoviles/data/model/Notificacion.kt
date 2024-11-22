package com.example.proyecto_plataformasmoviles.data.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Notificacion(
    val id: String = "",              // ID del documento en Firestore
    val usuarioId: String = "",       // ID del usuario que interactuó (quien generó la notificación)
    val perfilId: String = "",        // ID del perfil que recibe la notificación
    val tipo: Int = 0,                // Tipo de notificación (1 = Woof, 2 = Guau, etc.)
    val mensaje: String = "",         // Mensaje de la notificación
    val timestamp: Long = 0L,         // Marca de tiempo de la notificación
    val leido: Boolean = false        // Estado de lectura de la notificación
) {
    // Función para formatear el timestamp en una cadena legible
    fun getFormattedTimestamp(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return dateFormat.format(Date(timestamp))
    }
}
