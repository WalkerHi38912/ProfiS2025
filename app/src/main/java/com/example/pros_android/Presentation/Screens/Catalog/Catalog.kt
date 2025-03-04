package com.example.pros_android.Presentation.Screens.Catalog

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pros_android.Data.User.UserState
import com.example.pros_android.Data.User.model.Product
import com.example.pros_android.Presentation.Screens.Common.Categories
import com.example.pros_android.Presentation.Screens.Common.Dialog
import com.example.pros_android.Presentation.Screens.Common.LoadingComponent
import com.example.pros_android.Presentation.Screens.Common.LoadingDialog
import com.example.pros_android.Presentation.Screens.Common.ProductCard
import com.example.pros_android.Presentation.Screens.Common.getErrorMessage
import com.example.pros_android.Presentation.ViewModel.AuthViewModel
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Background_Prof
import com.example.pros_android.ui.theme.Block_Prof
import com.example.pros_android.ui.theme.SubTextLight_Prof
import com.example.pros_android.ui.theme.Text_Prof
import com.example.pros_android.ui.theme.newPeninimFontFamily

@ExperimentalMaterial3Api
@Composable
fun Catalog(
    authViewModel: AuthViewModel,
    navHostController: NavHostController
){
    val userState by authViewModel.userState
    var currentUserState by remember { mutableStateOf("") }

    val products by authViewModel.products
    val collectionItems by authViewModel.collectionItems

    var selectedCategory by remember { mutableStateOf(0) }
    var showLoadingDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }

    LaunchedEffect (Unit){
        authViewModel.getProductsList()
        Log.e("CatalogProductsList", "$products")
    }

    LoadingDialog(
        showDialog = showLoadingDialog
    ) {
        showLoadingDialog = false
    }

    Dialog(
        showDialog = showErrorDialog,
        errorText = getErrorMessage(currentUserState)
    ){
        showErrorDialog = false
    }

    Log.e("Catalog User State", currentUserState)
    when (userState) {
        is UserState.Loading -> {
            showLoadingDialog = true
        }

        is UserState.Success -> {
            val message = (userState as UserState.Success).message
            currentUserState = message
            showLoadingDialog = false
            showErrorDialog = false
        }

        is UserState.Error -> {
            val message = (userState as UserState.Error).message
            currentUserState = message
            showLoadingDialog = false
            showErrorDialog = true
        }

        is UserState.Update -> {}
    }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.Background))
    ){
        Column (
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
        ){

            CatalogTopAppBar("Каталог")
            Spacer(Modifier.height(16.dp))
            Categories(authViewModel, selectedCategory){ newCategory ->
                selectedCategory = newCategory
            }
            Log.e("Category", "$selectedCategory")
            Spacer(Modifier.height(20.dp))
            ProductsList(
                products = (if (selectedCategory == 0) products else collectionItems),
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun CatalogTopAppBar(header: String){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(colorResource(R.color.Main_Color), shape = (RoundedCornerShape(16.dp)))
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(20.dp)
    ){
        IconButton(
            onClick = {},
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .background(Block_Prof)
        ) {
            Icon(
                painter = painterResource(R.drawable.back),
                contentDescription = "Menu",
                tint = Color.Unspecified
            )
        }
        Text(
            text = header,
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 16.sp,
                color = colorResource(R.color.Block_Prof)
            ),
        )
        IconButton(
            onClick = {},
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .background(Color.Transparent)
        ) {
        }
    }
}

@Composable
fun ProductsList(products: List<Product>, navHostController: NavHostController){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Две колонки (по 50% каждая)
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp), // Отступы между строками
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Отступы между колонками
    ) {
        items(products) { product ->
            ProductCard(product, navHostController)
        }
    }
}

