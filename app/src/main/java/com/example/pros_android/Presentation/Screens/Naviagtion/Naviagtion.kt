package com.example.pros_android.Presentation.Screens.Naviagtion

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pros_android.Data.User.UserState
import com.example.pros_android.Presentation.Screens.Catalog.Catalog
import com.example.pros_android.Presentation.Screens.Favorite.FavoriteScreen
import com.example.pros_android.Presentation.Screens.Home.HomeScreen
import com.example.pros_android.Presentation.Screens.LogIn.LogInScreen
import com.example.pros_android.Presentation.Screens.MyCart.CartScreen
import com.example.pros_android.Presentation.Screens.SignUp.NewUserScreen
import com.example.pros_android.Presentation.Screens.OnBoard.OnboardMain
import com.example.pros_android.Presentation.Screens.Popular.PopularScreen
import com.example.pros_android.Presentation.ViewModel.AuthViewModel
import com.example.pros_android.ui.theme.Red_Prof

@ExperimentalMaterial3Api
@Composable
fun NavigationGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    val context = LocalContext.current
    val userState by authViewModel.userState
    var currentUserState by remember { mutableStateOf("") }
    var currentEnterPoint by remember { mutableStateOf("login") }

    LaunchedEffect (Unit){
        authViewModel.isUserLoggedIn(
            context
        )
    }
    /*

    when(userState){
        is UserState.Loading -> {

        }
        is UserState.Success -> {
            val message = (userState as UserState.Success).msg
            if (message == "User already logged in"){
                currentEnterPoint = "home"
            } else {
                currentEnterPoint = "login"
            }
        }
        is UserState.Error -> {
            val message = (userState as UserState.Error).msg
            currentUserState = message
        }
    }*/

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LogInScreen(navController, authViewModel)
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
        composable("newUser"){
            NewUserScreen(navController, authViewModel)
        }
    }
}