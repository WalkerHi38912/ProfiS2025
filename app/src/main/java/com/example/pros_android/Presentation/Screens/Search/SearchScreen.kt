package com.example.pros_android.Presentation.Screens.Search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pros_android.Data.User.UserState
import com.example.pros_android.Presentation.Screens.Catalog.CatalogTopAppBar
import com.example.pros_android.Presentation.Screens.Catalog.ProductsList
import com.example.pros_android.Presentation.Screens.Common.Categories
import com.example.pros_android.Presentation.Screens.Common.Dialog
import com.example.pros_android.Presentation.Screens.Common.LoadingDialog
import com.example.pros_android.Presentation.Screens.Common.getErrorMessage
import com.example.pros_android.Presentation.ViewModel.AuthViewModel
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Background_Prof
import com.example.pros_android.ui.theme.Block_Prof
import com.example.pros_android.ui.theme.Hint_Prof

@ExperimentalMaterial3Api
@Composable
fun Search(
    authViewModel: AuthViewModel,
    navHostController: NavHostController
){
    val userState by authViewModel.userState
    var currentUserState by remember { mutableStateOf("") }

    val products by authViewModel.products
    val searchResult by authViewModel.searchResult
    var searchQuery by remember { mutableStateOf("") }

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
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
        ){
            CatalogTopAppBar("Поиск", navHostController)
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                    ,
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        authViewModel.searchProducts(it)
                    },
                    placeholder = { Text("Поиск") },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Block_Prof,
                        unfocusedContainerColor = Block_Prof,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Hint_Prof,
                        unfocusedLabelColor = Hint_Prof,
                        focusedTextColor = Color.Unspecified,
                        unfocusedTextColor = Color.Unspecified,
                        cursorColor = Hint_Prof
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.marker),
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )
                Spacer(Modifier.width(10.dp))
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(52.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.group_1000000743),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            ProductsList(
                products =  (if (searchQuery == "") products else searchResult),
                navHostController = navHostController
            )
        }
    }
}
