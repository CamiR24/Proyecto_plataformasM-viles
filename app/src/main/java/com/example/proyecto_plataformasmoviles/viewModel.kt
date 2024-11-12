package com.example.proyecto_plataformasmoviles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.navigation.NavController

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _signInState = MutableLiveData<Boolean>()
    val signInState: LiveData<Boolean> get() = _signInState

    // Función para registrar un nuevo usuario
    fun registrarUsuario(email: String, password: String, navController: NavController) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate("InicioSesion")
                } else {
                    val errorMessage = task.exception?.message ?: "Error desconocido"
                    // Aquí puedes manejar el mensaje de error si lo necesitas
                }
            }
    }

    // Función para iniciar sesión con el correo y la contraseña
    fun signInWithEmailAndPassword(email: String, password: String, onComplete: (Boolean, String) -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Autenticación exitosa
                        onComplete(true, "Inicio de sesión exitoso")
                    } else {
                        // Error de autenticación
                        onComplete(false, task.exception?.message ?: "Error desconocido")
                    }
                }
        } else {
            onComplete(false, "El correo o la contraseña están vacíos.")
        }
    }
}
