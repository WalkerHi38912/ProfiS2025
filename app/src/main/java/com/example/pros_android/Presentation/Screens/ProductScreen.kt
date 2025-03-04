package com.example.pros_android.Presentation.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pros_android.Data.User.model.Product
import com.example.pros_android.Presentation.Screens.Catalog.CatalogTopAppBar
import com.example.pros_android.Presentation.Screens.Catalog.ProductsList
import com.example.pros_android.Presentation.Screens.Common.Categories
import com.example.pros_android.Presentation.ViewModel.AuthViewModel
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Background_Prof
import com.example.pros_android.ui.theme.Block_Prof
import com.example.pros_android.ui.theme.Red_Prof
import com.example.pros_android.ui.theme.Text_Prof
import com.example.pros_android.ui.theme.newPeninimFontFamily
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URLDecoder

@Composable
fun ProductScreen(
    navBackStackEntry: NavBackStackEntry
){
    val productJson = navBackStackEntry.arguments?.getString("product")
    val decodeUserJson = URLDecoder.decode(productJson, "UTF-8")
    val product = Json.decodeFromString<Product>(decodeUserJson)

    val imagesList = listOf(product.imageUrlMain, product.imageUrl1, product.imageUrl2, product.imageUrl3, product.imageUrl4)
    var mainImage by remember { mutableStateOf(product.imageUrlMain) }

    Box (
        modifier = Modifier
            .fillMaxSize()
            //.background(colorResource(R.color.Background))
    ){
        Column (
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
        ){

            CatalogTopAppBar("HI-FI'ечная")
            Spacer(Modifier.height(16.dp))
            Text(
                text = product.name,
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 24.sp,
                    color = colorResource(R.color.Text_Prof)
                )
            )
            Spacer(Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .background(colorResource(R.color.Block_Prof))
            ){
                AsyncImage(
                    model = mainImage,
                    contentDescription = product.name,
                    modifier = Modifier
                        .padding(20.dp)
                        .height(300.dp),
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            }
            Spacer(Modifier.height(8.dp))
            LazyRow {
                items(imagesList){ image ->
                    if (!image.isNullOrBlank()){
                        Box(
                            modifier = Modifier
                                .clickable {
                                    mainImage = image
                                }
                                .padding(end = 15.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(colorResource(R.color.Block_Prof))
                                .height(90.dp)
                                .width(60.dp)
                        ){
                            AsyncImage(
                                model = image,
                                contentDescription = product.name,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = product.price,
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.Text_Prof)
                )
            )
            /*
            Spacer(Modifier.height(8.dp))
            Text(
                text = product.description,
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 14.sp,
                    color = colorResource(R.color.SubTextLight_Prof)
                )
            )*/
            Spacer(Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .background(colorResource(R.color.Button_Grey))
                ) {
                    Icon(
                        painter = painterResource(R.drawable.favorite),
                        contentDescription = "Menu",
                        tint = Color.Unspecified
                    )
                }
                Spacer(Modifier.width(18.dp))
                Button(
                    onClick = {
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.Main_Color),
                        contentColor = Text_Prof,
                        disabledContainerColor = colorResource(R.color.Main_Color),
                        disabledContentColor = Text_Prof
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                        //.padding(5.dp)
                    ){
                        Icon(
                            painter = painterResource(R.drawable.bag_2),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(14.dp),
                            text = "В корзину",
                            style = TextStyle(
                                fontFamily = newPeninimFontFamily,
                                fontSize = 14.sp,
                                color = Background_Prof
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Spacer(Modifier.systemBarsPadding())
        }
    }
}