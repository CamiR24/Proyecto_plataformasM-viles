package com.example.proyecto_plataformasmoviles
import com.google.firebase.firestore.FirebaseFirestore
object Injection {
    private val instance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    fun instance(): FirebaseFirestore {
        return instance
    }
}