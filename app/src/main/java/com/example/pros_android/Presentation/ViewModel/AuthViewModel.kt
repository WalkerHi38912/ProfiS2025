package com.example.pros_android.Presentation.ViewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pros_android.Data.User.model.SupabaseClient.client
import com.example.pros_android.Data.User.UserState
import com.example.pros_android.Data.User.model.CollectionProductWrapper
import com.example.pros_android.Data.User.model.Product
import com.example.pros_android.Data.User.model.Promotions
import com.example.pros_android.utils.SharedPreferenceHelper
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.launch


class AuthViewModel : ViewModel() {
    private val _userState = mutableStateOf<UserState>(UserState.Update)
    val userState: State<UserState> = _userState

    private val _promotions = mutableStateOf<List<Promotions>>(emptyList())
    val promotions: State<List<Promotions>> = _promotions

    private val _products = mutableStateOf<List<Product>>(emptyList())
    val products: State<List<Product>> = _products

    private val _collectionItems = mutableStateOf<List<Product>>(emptyList())
    val collectionItems: State<List<Product>> = _collectionItems

    fun signUp(
        context: Context,
        userEmail: String,
        userPassword: String,
    ) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Update
                client.gotrue.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)
                _userState.value = UserState.Success("Registered successfully!")
            } catch(e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }

        }
    }

    private fun saveToken(context: Context) {
        viewModelScope.launch {
            val accessToken = client.gotrue.currentAccessTokenOrNull()
            val sharedPref = SharedPreferenceHelper(context)
            sharedPref.saveStringData("accessToken",accessToken)
        }

    }

    private fun getToken(context: Context): String? {
        val sharedPref = SharedPreferenceHelper(context)
        return sharedPref.getStringData("accessToken")
    }

    fun login(
        context: Context,
        userEmail: String,
        userPassword: String,
    ) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                client.gotrue.loginWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)
                _userState.value = UserState.Success("Logged in successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }

        }
    }

    fun logout(context: Context) {
        val sharedPref = SharedPreferenceHelper(context)
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                client.gotrue.logout()
                sharedPref.clearPreferences()
                _userState.value = UserState.Success("Logged out successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }
        }
    }

    fun isUserLoggedIn(
        context: Context,
    ) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                val token = getToken(context)
                if(token.isNullOrEmpty()) {
                    _userState.value = UserState.Success("User not logged in!")
                } else {
                    client.gotrue.retrieveUser(token)
                    client.gotrue.refreshCurrentSession()
                    saveToken(context)
                    _userState.value = UserState.Success("User already logged in!")
                }
            } catch (e: RestException) {
                _userState.value = UserState.Error(e.error)
            }
        }
    }

    fun upDateUserState(){
        _userState.value = UserState.Update
    }

    fun getPromo() {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                val data = client.postgrest["Promotions"]
                    .select()
                    .decodeList<Promotions>()
                Log.e("ViewModelGetPromo", "$data")
                _promotions.value = data
                _userState.value = UserState.Success("Promos loaded successfully")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Ошибка загрузки: ${e.message}")
            }
        }
    }

    fun getProductsList(){
        viewModelScope.launch {
            try{
                _userState.value = UserState.Loading
                val data = client.postgrest["products"]
                    .select()
                    .decodeList<Product>()
                Log.e("ViewModelGetProducts", "$data")
                _products.value = data
                _userState.value = UserState.Success("Products loaded successfully")
            } catch (e: Exception){
                _userState.value = UserState.Error("Ошибка загрузки: ${e.message}")
                Log.e("ViewModelGetProducts", "${e.message}")
            }
        }
    }

    fun getCollection(collectionId: Int){
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                val data = client.postgrest["collection_products"]
                    .select(columns = Columns.list("products(*)")){
                        eq("collection_id", collectionId)
                    }
                    .decodeList<CollectionProductWrapper>()
                    .map { it.products }
                _collectionItems.value = data
                _userState.value = UserState.Success("Collection loaded successfully")
                Log.e("ViewModelGetCollection", "$data")
            }catch (e: Exception){
                _userState.value = UserState.Error("Ошибка загрузки: ${e.message}")
                Log.e("ViewModelGetCollection", "${e.message}")
            }
        }
    }

}