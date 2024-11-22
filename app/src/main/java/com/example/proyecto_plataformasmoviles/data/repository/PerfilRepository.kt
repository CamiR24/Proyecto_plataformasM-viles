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

    // Crear un nuevo perfil con claves iniciando con mayúsculas
    suspend fun crearPerfil(perfil: Perfil): Result<String> {
        return try {
            // Transformar las propiedades del perfil a un Map con claves en formato deseado
            val perfilMap = perfil.toMapWithCapitalizedKeys()

            // Enviar el Map a la base de datos
            val documentRef = perfilesCollection.add(perfilMap).await()
            Result.success(documentRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Extensión para convertir un Perfil a un Map con claves iniciando con mayúsculas
    fun Perfil.toMapWithCapitalizedKeys(): Map<String, Any?> {
        return this::class.java.declaredFields.associate { field ->
            field.isAccessible = true
            field.name.capitalize() to field.get(this)
        }
    }

    // Extensión para capitalizar solo la primera letra
    fun String.capitalize(): String {
        return this.replaceFirstChar { it.uppercase() }
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
            Log.d("AUTH_USER", "Usuario obtenido correctamente con el nombre: " + perfil?.nombre_del_perro)
            Result.success(perfil)
        } catch (e: Exception) {
            Log.d("AUTH_USER", "Usuario obtenido incorrectamente con el id: " + userId)
            Result.failure(e)
        }
    }

    suspend fun obtenerPerfilesPorTamaño(tamaño: String): Result<List<Perfil>> {
        return try {
            val snapshot = perfilesCollection
                .whereEqualTo("Tamaño", tamaño)
                .get(Source.SERVER)
                .await()
            val perfiles = snapshot.documents.mapNotNull { it.toObject<Perfil>() }
            Result.success(perfiles)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun obtenerPerfilesPorRangoDePeso(minPeso: Int, maxPeso: Int): Result<List<Perfil>> {
        return try {
            val snapshot = perfilesCollection
                .whereGreaterThanOrEqualTo("Peso", minPeso)
                .whereLessThanOrEqualTo("Peso", maxPeso)
                .get(Source.SERVER)
                .await()
            val perfiles = snapshot.documents.mapNotNull { it.toObject<Perfil>() }
            Result.success(perfiles)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun obtenerPerfilesPorSexoDiferente(sexo: String): Result<List<Perfil>> {
        return try {
            val snapshot = perfilesCollection
                .whereNotEqualTo("Sexo", sexo)
                .get(Source.SERVER)
                .await()
            val perfiles = snapshot.documents.mapNotNull { it.toObject<Perfil>() }
            Result.success(perfiles)
        } catch (e: Exception) {
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
