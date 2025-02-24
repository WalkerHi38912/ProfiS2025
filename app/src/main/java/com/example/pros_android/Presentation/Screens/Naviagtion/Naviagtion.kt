package com.example.pros_android.Presentation.Screens.Naviagtion

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pros_android.Presentation.Screens.Catalog.Catalog
import com.example.pros_android.Presentation.Screens.Favorite.FavoriteScreen
import com.example.pros_android.Presentation.Screens.Home.HomeScreen
import com.example.pros_android.Presentation.Screens.Common.LoadingComponent
import com.example.pros_android.Presentation.Screens.LogIn.LogInScreen
import com.example.pros_android.Presentation.Screens.LogIn.TestScreen
import com.example.pros_android.Presentation.Screens.MyCart.CartScreen
import com.example.pros_android.Presentation.Screens.SignUp.NewUserScreen
import com.example.pros_android.Presentation.Screens.OnBoard.OnboardMain
import com.example.pros_android.Presentation.Screens.Popular.PopularScreen
import com.example.pros_android.Presentation.ViewModel.AuthViewModel

@ExperimentalMaterial3Api
@Composable
fun NavigationGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LogInScreen(navController, authViewModel)
        }
        composable("onboard1"){
            OnboardMain(navController)
        }
        composable("home") {
            HomeScreen(authViewModel)
        }
        composable("pop"){
            PopularScreen()
        }
        composable("catalog"){
            Catalog(authViewModel)
        }
        composable("favorite"){
            FavoriteScreen()
        }
        composable("cart"){
            CartScreen()
        }
        composable("newUser"){
            NewUserScreen(navController, authViewModel)
        }
        composable("test"){
            TestScreen(authViewModel)
        }
        composable("loading"){
            LoadingComponent()
        }
    }
}