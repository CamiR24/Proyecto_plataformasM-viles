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
import kotlinx.coroutines.launch
class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // LiveData para manejar los estados de la autenticación
    private val _authState = MutableLiveData<Boolean>()
    val authState: LiveData<Boolean> = _authState

    // Función para registrar al usuario y guardar su información
    fun PantallaRegistro(email: String, password: String, dogName: String, dogBreed: String, dogAge: String, dogWeight: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Si el registro es exitoso, obtenemos el usuario y lo agregamos a la base de datos
                    val user = auth.currentUser
                    val db = Firebase.firestore
                    val userProfile = hashMapOf(
                        "email" to email,
                        "dogName" to dogName,
                        "dogBreed" to dogBreed,
                        "dogAge" to dogAge,
                        "dogWeight" to dogWeight
                    )

                    // Guardamos los datos del usuario en Firestore
                    user?.let {
                        db.collection("perfiles").document(it.uid).set(userProfile)
                            .addOnSuccessListener {
                                _authState.value = true
                            }
                            .addOnFailureListener { e ->
                                _authState.value = false
                            }
                    }
                } else {
                    _authState.value = false
                }
            }
    }
}
