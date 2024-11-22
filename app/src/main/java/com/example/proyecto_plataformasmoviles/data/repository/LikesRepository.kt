package com.example.proyecto_plataformasmoviles.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LikesRepository {

    private val db = FirebaseFirestore.getInstance()
    private val likesCollection = db.collection("Likes")

    // Dar "like" a un perfil
    suspend fun darLike(
        usuarioId: String,
        perfilId: String,
        matchesRepository: MatchesRepository,
        notificacionesRepository: NotificacionesRepository,
        onNavigate: (String, String) -> Unit // Callback para manejar la navegación
    ): Result<Unit> {
        return try {
            // Dar like
            val like = hashMapOf(
                "usuario_id" to usuarioId,
                "perfil_id" to perfilId,
            )
            likesCollection.add(like).await()

            // Notificación de tipo "like"
            notificacionesRepository.crearNotificacion(
                usuarioId = usuarioId,
                perfilId = perfilId,
                tipo = 1, // Tipo de notificación: Like
                mensaje = "¡El usuario $usuarioId te dio un like!"
            )


            // Verificar si el perfil ya dio like de vuelta
            val likeSnapshot = likesCollection
                .whereEqualTo("usuario_id", perfilId)
                .whereEqualTo("perfil_id", usuarioId)
                .get()
                .await()

            if (likeSnapshot.documents.isNotEmpty()) {
                // Verificar si ya existe un match
                val matchExists = matchesRepository.verificarMatch(usuarioId, perfilId).getOrDefault(false)
                if (!matchExists) {
                    // Crear match y navegar
                    matchesRepository.crearMatch(usuarioId, perfilId, notificacionesRepository) { id1, id2 ->
                        onNavigate(id1, id2) // Navegar a la pantalla del match
                    }
                }
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Quitar "like" de un perfil
    suspend fun quitarLike(usuarioId: String, perfilId: String): Result<Unit> {
        return try {
            val snapshot = likesCollection
                .whereEqualTo("usuario_id", usuarioId)
                .whereEqualTo("perfil_id", perfilId)
                .get()
                .await()
            snapshot.documents.firstOrNull()?.reference?.delete()?.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Obtener los perfiles a los que un usuario ha dado "like"
    suspend fun obtenerLikesPorUsuario(usuarioId: String): Result<List<String>> {
        return try {
            val snapshot = likesCollection
                .whereEqualTo("usuario_id", usuarioId)
                .get()
                .await()
            val perfilIds = snapshot.documents.mapNotNull { it.getString("perfil_id") }
            Result.success(perfilIds)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Contar likes que tiene un perfil
    suspend fun contarLikesPorPerfil(perfilId: String): Result<Int> {
        return try {
            val snapshot = likesCollection
                .whereEqualTo("perfil_id", perfilId)
                .get()
                .await()
            Result.success(snapshot.size()) // Contamos los documentos
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    // Verificar si un usuario ya dio "like" a un perfil
    suspend fun verificarLike(usuarioId: String, perfilId: String): Result<Boolean> {
        return try {
            val snapshot = likesCollection
                .whereEqualTo("usuario_id", usuarioId)
                .whereEqualTo("perfil_id", perfilId)
                .get()
                .await()
            val exists = snapshot.documents.isNotEmpty()
            Result.success(exists)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
