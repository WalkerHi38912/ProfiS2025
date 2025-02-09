package com.example.pros_android.Presentation.Screens.Main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.pros_android.Presentation.Screens.Naviagtion.NavigationGraph
import com.example.pros_android.Presentation.ViewModel.AuthViewModel
import com.example.pros_android.ui.theme.Pros_AndroidTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel()
            NavigationGraph(navController, authViewModel)
        }
    }
}