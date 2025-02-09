package com.example.pros_android.Presentation.ViewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pros_android.Data.User.SupabaseClientProvider
import com.example.pros_android.Data.User.UserState
import com.example.pros_android.utils.SharePrefHelper
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel() {
    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState

    fun signUp(
        context: Context,
        userEmail: String,
        userPassword: String
    ){
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                SupabaseClientProvider.client.auth.signUpWith(Email){
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)
                _userState.value = UserState.Success("Registered successfully")
            } catch (e: Exception){
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }

    }

    fun logIn(
        context: Context,
        userEmail: String,
        userPassword: String
    ){
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                SupabaseClientProvider.client.auth.signInWith(Email){
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)
                _userState.value = UserState.Success("Logged in successfully")
            } catch (e: Exception){
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    fun logOut(context: Context){
        val sharePref = SharePrefHelper(context)
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                SupabaseClientProvider.client.auth.signOut()
                sharePref.clearPreferences()
                _userState.value = UserState.Success("Logged out successfully")
            } catch (e: Exception){
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    fun isUserLoggedIn(
        context: Context
    ){
        viewModelScope.launch {
            try {
                val token = getToken(context)
                if (token.isNullOrEmpty()){
                    _userState.value = UserState.Error("User is not logged in")
                } else {
                    SupabaseClientProvider.client.auth.retrieveUser(token)
                    SupabaseClientProvider.client.auth.refreshCurrentSession()
                    saveToken(context)
                    _userState.value = UserState.Success("User already logged in")
                }
            } catch (e: Exception){
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    private fun saveToken(context: Context){
        viewModelScope.launch {
            val accessToken = SupabaseClientProvider.client.auth.currentAccessTokenOrNull()
            val sharedPreferences = SharePrefHelper(context)
            sharedPreferences.saveStringData("accessToken", accessToken)
        }
    }

    private fun getToken(context: Context) : String? {
        val sharedPreferences = SharePrefHelper(context)
        return sharedPreferences.getStringData("accessToken")
    }
}