package com.example.proyecto_plataformasmoviles

import com.example.proyecto_plataformasmoviles.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.example.proyecto_plataformasmoviles.Injection
import com.example.proyecto_plataformasmoviles.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import androidx.navigation.NavController
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registrarUsuario(email: String, password: String, navController: NavController) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    navController.navigate("InicioSesion") {
                    }
                } else {
                    val errorMessage = task.exception?.message ?: "Error desconocido"

                }
            }
    }
}
