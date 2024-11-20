package com.example.proyecto_plataformasmoviles

sealed class Screen(val route:String) {
    object LoginScreen:Screen("loginscreen")
    object SignupScreen:Screen("loginscreen")
    object ChatRoomsScreen:Screen("loginscreen")
    object ChatScreen:Screen("loginscreen")
}