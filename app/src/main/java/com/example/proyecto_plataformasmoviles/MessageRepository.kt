package com.example.proyecto_plataformasmoviles

import com.google.firebase.firestore.FirebaseFirestore
import com.example.proyecto_plataformasmoviles.Result
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
class MessageRepository(private val firestore: FirebaseFirestore) {
    suspend fun sendMessage(roomId: String, message: Message): Result<Unit> = try {
        firestore.collection("rooms").document(roomId)
            .collection("messages").add(message).await()
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
    fun getChatMessages(roomId: String): Flow<List<Message>> = callbackFlow {
        val subscription = firestore.collection("rooms").document(roomId)
            .collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { querySnapshot, _ ->
                querySnapshot?.let {
                    trySend(it.documents.map { doc ->
                        doc.toObject(Message::class.java)!!.copy()
                    }).isSuccess
                }
            }
        awaitClose { subscription.remove() }
    }
}