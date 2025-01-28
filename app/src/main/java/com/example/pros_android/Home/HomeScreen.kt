package com.example.pros_android.Home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Background_Prof
import com.example.pros_android.ui.theme.Block_Prof
import com.example.pros_android.ui.theme.Hint_Prof
import com.example.pros_android.ui.theme.SubTextDark_Prof
import com.example.pros_android.ui.theme.Text_Prof
import com.example.pros_android.ui.theme.newPeninimFontFamily
import com.google.android.material.bottomappbar.BottomAppBar

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(){
    Scaffold(
        modifier = Modifier
            .background(Background_Prof),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "ГЛАВНАЯ",
                        style = TextStyle(
                            fontFamily = newPeninimFontFamily,
                            fontSize = 32.sp,
                            color = Text_Prof
                        ),
                    )},
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.hamburger),
                            contentDescription = "Menu",
                            tint = Color.Unspecified
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(44.dp)
                            .background(Color.Unspecified)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.group_27),
                            contentDescription = "Card",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            SearchAndFilter(paddingValues)
        }
    )
}

@Composable
fun TopAppBar(){

}
@Composable
fun SearchAndFilter(paddingValues: PaddingValues){
    var searchQuery by remember { mutableStateOf("") }

    Row (
        modifier = Modifier
            .padding(paddingValues)
            .padding(start = 20.dp, end = 20.dp, top = 21.dp)
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
        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(start = 14.dp)
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