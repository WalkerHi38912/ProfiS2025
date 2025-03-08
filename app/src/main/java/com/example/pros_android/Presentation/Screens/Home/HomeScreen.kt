package com.example.pros_android.Presentation.Screens.Home

import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.pros_android.Data.User.UserState
import com.example.pros_android.Data.User.model.Product
import com.example.pros_android.Data.User.model.Promotions
import com.example.pros_android.Presentation.Screens.Catalog.ProductsList
import com.example.pros_android.Presentation.Screens.Common.Categories
import com.example.pros_android.Presentation.Screens.Common.LoadingComponent
import com.example.pros_android.Presentation.Screens.Common.ProductCard
import com.example.pros_android.Presentation.ViewModel.AuthViewModel
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Accent_Prof
import com.example.pros_android.ui.theme.Background_Prof
import com.example.pros_android.ui.theme.Block_Prof
import com.example.pros_android.ui.theme.Hint_Prof
import com.example.pros_android.ui.theme.SubTextDark_Prof
import com.example.pros_android.ui.theme.SubTextLight_Prof
import com.example.pros_android.ui.theme.Text_Prof
import com.example.pros_android.ui.theme.newPeninimFontFamily
import com.google.android.material.bottomappbar.BottomAppBar
import io.ktor.http.Url

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    navHostController: NavHostController
) {
    val promotions by authViewModel.promotions

    val userState by authViewModel.userState
    val products by authViewModel.products
    val collectionItems by authViewModel.collectionItems

    var currentUserState by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(0) }

    LaunchedEffect (Unit){
        authViewModel.getPromo()
        authViewModel.getProductsList()
        Log.e("HomeScreenPromos","$promotions")
    }

    when (userState) {
        is UserState.Loading -> {
            //LoadingComponent()
        }

        is UserState.Success -> {
            val message = (userState as UserState.Success).message
            currentUserState = message
        }

        is UserState.Error -> {
            val message = (userState as UserState.Error).message
            currentUserState = message
        }

        is UserState.Update -> {}
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.Background))
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    //.padding(start = 20.dp, end = 20.dp)
            ) {
                Column {
                    TopAppBar()
                    Spacer(Modifier.height(21.dp))
                    //SearchAndFilter(navHostController)
                    Spacer(Modifier.height(21.dp))
                    Row{
                        Spacer(Modifier.width(10.dp))
                        IconButton(
                            onClick = {
                                navHostController.navigate("Search")
                            },
                            modifier = Modifier
                                .shadow(8.dp, shape = RoundedCornerShape(40.dp))
                                .background(colorResource(R.color.Block_Prof))
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.marker),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                        //Spacer(Modifier.width(10.dp))
                        Categories(authViewModel, selectedCategory){ newCategory ->
                            selectedCategory = newCategory
                        }
                    }
                    Spacer(Modifier.height(24.dp))
                    Popular(products, collectionItems, selectedCategory, navHostController)
                }
            }
        }
        BottomAppBar(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun TopAppBar(){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
    ){
        IconButton(
            onClick = {}
        ) {
            Icon(
                painter = painterResource(R.drawable.hamburger),
                contentDescription = "Menu",
                tint = Color.Unspecified
            )
        }
        Text(
            text = "HI-FI'ечная",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 32.sp,
                color = Text_Prof
            ),
        )
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(44.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                painter = painterResource(R.drawable.group_27),
                contentDescription = "Card",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .background(colorResource(R.color.Block_Prof))

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
        ){
            IconButton(
                onClick = {},
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(R.drawable.home),
                    contentDescription = "Card",
                    tint = Color.Unspecified
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(R.drawable.favorite),
                    contentDescription = "Card",
                    tint = Color.Unspecified
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(R.drawable.bag_black),
                    contentDescription = "Card",
                    tint = Color.Unspecified
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(R.drawable.notification),
                    contentDescription = "Card",
                    tint = Color.Unspecified
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Card",
                    tint = Color.Unspecified
                )
            }
        }
    }
}
/*
@Composable
fun SearchAndFilter(
    navHostController: NavHostController
){
    var searchQuery by remember { mutableStateOf("") }

    Row (
        modifier = Modifier
            .height(52.dp)
    ){
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxHeight()
                .background(colorResource(R.color.Block_Prof))
                .weight(1f)
                .clickable {
                    navHostController.navigate("Search")
                }
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxSize()
            ){
                Icon(
                    painter = painterResource(R.drawable.marker),
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Поиск",
                    style = TextStyle(
                        fontFamily = newPeninimFontFamily,
                        fontSize = 16.sp,
                        color = Text_Prof
                    )
                )
            }
        }
        Spacer(Modifier.width(14.dp))
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
}*/

@Composable
fun Popular(products: List<Product>, collectionItems: List<Product>, selectedCategory: Int, navHostController: NavHostController){
    Column (
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 80.dp)
    ){
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = "Популярное",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 16.sp,
                    color = Text_Prof
                )
            )
            Text(
                text = "Все",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 14.sp,
                    color = colorResource(R.color.Main_Color)
                ),
                modifier = Modifier
                    .clickable {
                        navHostController.navigate("catalog")
                    }
            )
        }
        Spacer(Modifier.height(30.dp))
        ProductsList(
            products = (if (selectedCategory == 0) products else collectionItems),
            navHostController = navHostController
        )

    }
}

@Composable
fun Sells(promotions: List<Promotions>){
    var promoUrl by remember { mutableStateOf("") }
    Log.e("promoUrl", promoUrl)

    LaunchedEffect(promotions) {
        promotions.firstOrNull()?.let { promo ->
            promoUrl = promo.imageUrl
        }
    }

    Column {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = "Акции",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 16.sp,
                    color = Text_Prof
                )
            )
            Text(
                text = "Все",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 12.sp,
                    color = Accent_Prof
                )
            )
        }
        Spacer(Modifier.height(20.dp))

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .decoderFactory(SvgDecoder.Factory())
                .data(promoUrl)
                .size(Size.ORIGINAL) // Set the target size to load the image at.
                .build()
        )
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}