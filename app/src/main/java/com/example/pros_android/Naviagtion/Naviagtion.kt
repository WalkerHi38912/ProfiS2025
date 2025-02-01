package com.example.pros_android.Naviagtion

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pros_android.Catalog.Catalog
import com.example.pros_android.Favorite.FavoriteScreen
import com.example.pros_android.Home.HomeScreen
import com.example.pros_android.LogIn.LogInScreen
import com.example.pros_android.MyCart.CartScreen
import com.example.pros_android.OnBoard.Onboard1
import com.example.pros_android.OnBoard.OnboardMain
import com.example.pros_android.popular.PopularScreen

@ExperimentalMaterial3Api
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "cart") {
        composable("login") {
            LogInScreen(navController)
        }
        composable("onboard1"){
            OnboardMain(navController)
        }
        composable("home") {
            HomeScreen()
        }
        composable("pop"){
            PopularScreen()
        }
        composable("catalog"){
            Catalog()
        }
        composable("favorite"){
            FavoriteScreen()
        }
        composable("cart"){
            CartScreen()
        }
    }
}