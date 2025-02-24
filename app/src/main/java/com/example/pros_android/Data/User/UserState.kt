package com.example.pros_android.Data.User

sealed class UserState {
    object Loading: UserState()
    data class Success(val message: String): UserState()
    data class Error(val message: String): UserState()
    object Update: UserState()
}