package com.example.pros_android.Common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pros_android.Home.CategoryBox
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Accent_Prof
import com.example.pros_android.ui.theme.Block_Prof
import com.example.pros_android.ui.theme.Hint_Prof
import com.example.pros_android.ui.theme.SubTextLight_Prof
import com.example.pros_android.ui.theme.Text_Prof
import com.example.pros_android.ui.theme.newPeninimFontFamily

fun validateEmail(email: String): Boolean {
    val regex = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$".toRegex()
    return regex.matches(email)
}

@Composable
fun ProductCard(){
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .width(160.dp)
            .height(182.dp)
            .background(Block_Prof)
            .padding(start = 9.dp)
    ) {
        Box{
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(top = 9.dp, bottom = 9.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .size(27.dp)
                    .background(SubTextLight_Prof)
            ) {
                Icon(
                    painter = painterResource(R.drawable.favorite),
                    contentDescription = null
                )
            }
            Image(
                painter = painterResource(R.drawable.nikesho),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 18.dp, bottom = 12.dp)
                    .height(70.dp)
                    .width(142.dp)
                    .graphicsLayer(
                        scaleX = -1f,
                    ),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "BEST SELLER",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 12.sp,
                color = Accent_Prof
            )
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Nike Air Max",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 16.sp,
                color = Hint_Prof
            )
        )
        Spacer(Modifier.height(14.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = "₽752.00",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Text_Prof
                )
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 16.dp))
                    .size(34.dp)
                    .background(Accent_Prof)
            ){
                Icon(
                    painter = painterResource(R.drawable.cart),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}


@Composable
fun Categories(){
    val itemsList = listOf("Все", "Outdoor", "Tennis", "Item 4", "Item 5")
    Column {
        Text(
            text = "Категории",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 16.sp,
                color = Text_Prof
            )
        )
        Spacer(Modifier.height(19.dp))
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(itemsList){ item ->
                CategoryBox(item)
            }
        }
    }

}