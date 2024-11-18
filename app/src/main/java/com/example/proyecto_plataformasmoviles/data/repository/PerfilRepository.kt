package com.example.proyecto_plataformasmoviles.data.repository

import android.util.Log
import com.example.proyecto_plataformasmoviles.data.model.Perfil
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.Source
import kotlinx.coroutines.tasks.await

class PerfilesRepository {

    private val db = FirebaseFirestore.getInstance()
    private val perfilesCollection = db.collection("Perfiles")

    // Crear un nuevo perfil
    suspend fun crearPerfil(perfil: Perfil): Result<String> {
        return try {
            val documentRef = perfilesCollection.add(perfil).await()
            Result.success(documentRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Leer todos los perfiles
    suspend fun obtenerPerfiles(): Result<List<Perfil>> {
        return try {
            val snapshot = perfilesCollection.get().await()
            val perfiles = snapshot.documents.mapNotNull { it.toObject<Perfil>() }
            Result.success(perfiles)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Leer un perfil por ID
    suspend fun obtenerPerfilPorId(id: String): Result<Perfil?> {
        return try {
            val snapshot = perfilesCollection.document(id).get().await()
            val perfil = snapshot.toObject<Perfil>()
            Result.success(perfil)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun obtenerPerfilesPorUbicacion(ubicacion: String): Result<List<Perfil>> {
        return try {
            val snapshot = perfilesCollection
                .whereEqualTo("Ubicacion", ubicacion)
                .get(Source.SERVER) // Fuerza a obtener datos del servidor, sin caché
                .await()

            // Logea los datos originales del documento para verificar la existencia de `Usuario_id`
            snapshot.documents.forEach { document ->
                Log.d("PROFILE_FIRESTORE", "Documento crudo: ${document.data}")
            }

            val perfiles = snapshot.documents.mapNotNull { it.toObject<Perfil>() }
            Log.d("PROFILE_FIRESTORE", "Cargando perfiles mapeados: $perfiles")
            Result.success(perfiles)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    // Obtener perfiles por edad específica
    suspend fun obtenerPerfilesPorEdad(edad: Int): Result<List<Perfil>> {
        return try {
            val snapshot = perfilesCollection
                .whereEqualTo("Edad_del_perro", edad)
                .get(Source.SERVER) // Fuerza a obtener datos del servidor, sin caché
                .await()
            val perfiles = snapshot.documents.mapNotNull { it.toObject<Perfil>() }
            Result.success(perfiles)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Obtener perfiles por raza específica
    suspend fun obtenerPerfilesPorRaza(raza: String): Result<List<Perfil>> {
        return try {
            val snapshot = perfilesCollection
                .whereEqualTo("Raza_del_perro", raza)
                .get(Source.SERVER) // Fuerza a obtener datos del servidor, sin caché
                .await()
            val perfiles = snapshot.documents.mapNotNull { it.toObject<Perfil>() }
            Result.success(perfiles)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun obtenerPerfilPorUsuario(userId: String): Result<Perfil?> {
        return try {
            val snapshot = perfilesCollection.whereEqualTo("Usuario_id", userId).get().await()
            val perfil = snapshot.documents.firstOrNull()?.toObject<Perfil>() // Obtén el primer documento
            Log.d("AUTH_USER", "Usuario obtenido correctamente con el id: " + userId)
            Result.success(perfil)
        } catch (e: Exception) {
            Log.d("AUTH_USER", "Usuario obtenido incorrectamente con el id: " + userId)
            Result.failure(e)
        }
    }

    // Actualizar un perfil
    suspend fun actualizarPerfil(id: String, perfil: Perfil): Result<Unit> {
        return try {
            perfilesCollection.document(id).set(perfil).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Eliminar un perfil
    suspend fun eliminarPerfil(id: String): Result<Unit> {
        return try {
            perfilesCollection.document(id).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
