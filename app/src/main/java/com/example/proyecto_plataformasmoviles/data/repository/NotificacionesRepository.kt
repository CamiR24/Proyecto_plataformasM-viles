package com.example.proyecto_plataformasmoviles.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

data class Notificacion(
    val id: String = "",
    val usuarioId: String = "",
    val tipo: Int = 0,
    val mensaje: String = "",
    val timestamp: Long = 0L,
    val leido: Boolean = false
)

class NotificacionesRepository {

    private val db = FirebaseFirestore.getInstance()
    private val notificacionesCollection = db.collection("Notificaciones")

    // Crear una notificación para un usuario
    suspend fun crearNotificacion(usuarioId: String, tipo: Int, mensaje: String): Result<Unit> {
        return try {
            val notificacion = hashMapOf(
                "usuario_id" to usuarioId,
                "tipo" to tipo,
                "mensaje" to mensaje,
                "timestamp" to System.currentTimeMillis(),
                "leido" to false
            )
            notificacionesCollection.add(notificacion).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Obtener notificaciones
    suspend fun obtenerNotificacionesDeUsuario(usuarioId: String): Result<List<Notificacion>> {
        return try {
            val snapshot = notificacionesCollection
                .whereEqualTo("usuario_id", usuarioId)
                .get()
                .await()

            val notificaciones = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Notificacion::class.java)?.copy(id = doc.id)
            }

            // Retornar el resultado exitoso
            Result.success(notificaciones)
        } catch (e: Exception) {
            // Retornar el error si ocurre una excepción
            Result.failure(e)
        }
    }



    // Marcar como leída
    suspend fun marcarNotificacionComoLeida(notificacionId: String): Result<Unit> {
        return try {
            val notificacionRef = notificacionesCollection.document(notificacionId)
            notificacionRef.update("leido", true).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
