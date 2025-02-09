package com.example.pros_android.Data.User

sealed class UserState{
    object Loading: UserState()
    data class Success(val msg: String): UserState()
    data class Error(val msg: String): UserState()
}