package com.example.pros_android.Presentation.Screens.Home

import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pros_android.Presentation.Screens.Common.Categories
import com.example.pros_android.Presentation.Screens.Common.ProductCard
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

@ExperimentalMaterial3Api
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background_Prof)
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Column {
                    TopAppBar()
                    Spacer(Modifier.height(21.dp))
                    SearchAndFilter()
                    Spacer(Modifier.height(21.dp))
                    Categories()
                    Spacer(Modifier.height(24.dp))
                    Popular()
                    Spacer(Modifier.height(29.dp))
                    Sells()
                }
            }
            Spacer(Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.bottom_app_bar),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
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
            text = "ГЛАВНАЯ",
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
fun SearchAndFilter(){
    var searchQuery by remember { mutableStateOf("") }

    Row (
        modifier = Modifier
            .height(52.dp)
    ){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
            ,
            value = searchQuery,
            onValueChange = {searchQuery = it},
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
}

@Composable
fun CategoryBox(text: String){
    Button(
        onClick = {},
        modifier = Modifier
            .height(40.dp)
            .width(108.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Block_Prof
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 12.sp,
                color = Text_Prof
            )
        )
    }
}

@Composable
fun Popular(){
    Column {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = "Категории",
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
        Spacer(Modifier.height(30.dp))
        Row {
            ProductCard()
            Spacer(Modifier.width(15.dp))
            ProductCard()
        }

    }
}

@Composable
fun Sells(){
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
        Image(
            painter = painterResource(R.drawable.frame_1000000849),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .align(Alignment.CenterHorizontally)
        )


    }
}