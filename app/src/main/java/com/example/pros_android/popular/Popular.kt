package com.example.pros_android.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pros_android.Common.Categories

import com.example.pros_android.Home.Popular
import com.example.pros_android.Home.SearchAndFilter
import com.example.pros_android.Home.Sells
import com.example.pros_android.Home.TopAppBar
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Background_Prof
import com.example.pros_android.ui.theme.Block_Prof
import com.example.pros_android.ui.theme.Text_Prof
import com.example.pros_android.ui.theme.newPeninimFontFamily

@Composable
fun PopularScreen(){
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Background_Prof)
    ){
        Column (
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
        ){

            PopTopAppBar()
        }
    }
}

@Composable
fun PopTopAppBar(){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
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
            text = "Популярное",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 32.sp,
                color = Text_Prof
            ),
        )
        IconButton(
            onClick = {},
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .background(Block_Prof)
        ) {
            Icon(
                painter = painterResource(R.drawable.favorite),
                contentDescription = "Menu",
                tint = Color.Unspecified
            )
        }
    }
}