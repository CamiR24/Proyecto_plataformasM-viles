package com.example.proyecto_plataformasmoviles

import com.google.firebase.firestore.FirebaseFirestore
import com.example.proyecto_plataformasmoviles.Result
import kotlinx.coroutines.tasks.await
class RoomRepository(private val firestore: FirebaseFirestore) {
    suspend fun createRoom(name: String): Result<Unit> = try {
        val room = Room(name = name)
        firestore.collection("rooms").add(room).await()
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
    suspend fun getRooms(): Result<List<Room>> = try {
        val querySnapshot = firestore.collection("rooms").get().await()
        val rooms = querySnapshot.documents.map { document ->
            document.toObject(Room::class.java)!!.copy(id = document.id)
        }
        Result.Success(rooms)
    } catch (e: Exception) {
        Result.Error(e)
    }
}