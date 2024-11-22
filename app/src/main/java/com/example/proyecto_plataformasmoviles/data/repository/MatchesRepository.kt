package com.example.proyecto_plataformasmoviles.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MatchesRepository {

    private val db = FirebaseFirestore.getInstance()
    private val matchesCollection = db.collection("Matches")

    // Crear un match entre dos usuarios
    suspend fun crearMatch(usuarioId1: String, usuarioId2: String, notificacionesRepository: NotificacionesRepository): Result<Unit> {
        return try {
            val match = hashMapOf(
                "usuario1" to usuarioId1,
                "usuario2" to usuarioId2,
                "timestamp" to System.currentTimeMillis()
            )
            matchesCollection.add(match).await()

            //Crear notificaciones de tipo "match" para ambos usuarios

            notificacionesRepository.crearNotificacion(
                usuarioId = usuarioId1,
                tipo = 2,
                mensaje = "¡Hiciste match con el usuario $usuarioId2"
            )

            notificacionesRepository.crearNotificacion(
                usuarioId = usuarioId2,
                tipo = 2,
                mensaje = "!Hiciste match con el usuario $usuarioId1"
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Verificar si ya existe un match entre dos usuarios
    suspend fun verificarMatch(usuarioId1: String, usuarioId2: String): Result<Boolean> {
        return try {
            val snapshot = matchesCollection
                .whereEqualTo("usuario1", usuarioId1)
                .whereEqualTo("usuario2", usuarioId2)
                .get()
                .await()

            val reverseSnapshot = matchesCollection
                .whereEqualTo("usuario1", usuarioId2)
                .whereEqualTo("usuario2", usuarioId1)
                .get()
                .await()

            val exists = snapshot.documents.isNotEmpty() || reverseSnapshot.documents.isNotEmpty()
            Result.success(exists)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Eliminar un match entre dos usuarios
    suspend fun eliminarMatch(usuarioId1: String, usuarioId2: String): Result<Unit> {
        return try {
            val snapshot = matchesCollection
                .whereEqualTo("usuario1", usuarioId1)
                .whereEqualTo("usuario2", usuarioId2)
                .get()
                .await()

            val reverseSnapshot = matchesCollection
                .whereEqualTo("usuario1", usuarioId2)
                .whereEqualTo("usuario2", usuarioId1)
                .get()
                .await()

            // Eliminar ambos documentos si existen
            snapshot.documents.forEach { it.reference.delete().await() }
            reverseSnapshot.documents.forEach { it.reference.delete().await() }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Obtener todos los matches del usuario actual
    suspend fun obtenerMatchesDeUsuario(usuarioId: String): Result<List<String>> {
        return try {
            val snapshot = matchesCollection
                .whereEqualTo("usuario1", usuarioId)
                .get()
                .await()

            val reverseSnapshot = matchesCollection
                .whereEqualTo("usuario2", usuarioId)
                .get()
                .await()

            // Extraemos los IDs de los usuarios con los que se hizo match
            val matches = snapshot.documents.mapNotNull { it.getString("usuario2") } +
                    reverseSnapshot.documents.mapNotNull { it.getString("usuario1") }

            Result.success(matches)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
