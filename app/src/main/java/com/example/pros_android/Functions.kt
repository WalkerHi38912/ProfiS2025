package com.example.pros_android

fun validateEmail(email: String): Boolean {
    val regex = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$".toRegex()
    return regex.matches(email)
}