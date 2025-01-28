package com.example.pros_android.Common

fun validateEmail(email: String): Boolean {
    val regex = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$".toRegex()
    return regex.matches(email)
}