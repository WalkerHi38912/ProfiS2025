package com.example.pros_android.MyCart

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Accent_Prof
import com.example.pros_android.ui.theme.Background_Prof
import com.example.pros_android.ui.theme.Block_Prof
import com.example.pros_android.ui.theme.Hint_Prof
import com.example.pros_android.ui.theme.Red_Prof
import com.example.pros_android.ui.theme.SubTextDark_Prof
import com.example.pros_android.ui.theme.SubTextLight_Prof
import com.example.pros_android.ui.theme.Text_Prof
import com.example.pros_android.ui.theme.newPeninimFontFamily

@Composable
fun CartScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background_Prof)
    ){
        Column (
            modifier = Modifier
                .statusBarsPadding()
                .padding(start = 20.dp, end = 20.dp)
        ){
            Spacer(Modifier.height(10.dp))
            CartTopAppBar()
            Spacer(Modifier.height(16.dp))
            Text(
                text = "3 товара",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 16.sp,
                    color = Text_Prof
                )
            )
            Spacer(Modifier.height(16.dp))
            CartCard()
            CartCard()
            CartCard()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            BottomCounter()
        }
    }
}

@Composable
fun CartTopAppBar(){
    Box{
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
        ){
            Spacer(Modifier.weight(1f))
            Text(
                text = "Популярное",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 32.sp,
                    color = Text_Prof
                )
            )
            Spacer(Modifier.weight(1f))
        }
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
    }
}

@Composable
fun CartCard(){
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(104.dp)
            .background(Block_Prof)
            .padding(start = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Background_Prof)
                .align(Alignment.CenterVertically)
        ){
            Image(
                painter = painterResource(R.drawable.nikesho),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp)
                    .graphicsLayer(
                        scaleX = -1f,
                    ),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(Modifier.weight(0.5f))
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ){
            Column {
                Text(
                    text = "Nike Club Max",
                    style = TextStyle(
                        fontFamily = newPeninimFontFamily,
                        fontSize = 16.sp,
                        color = Text_Prof
                    )
                )
                Spacer(Modifier.height(9.dp))
                Text(
                    text = "₽94.05",
                    style = TextStyle(
                        fontFamily = newPeninimFontFamily,
                        fontSize = 14.sp,
                        color = Hint_Prof
                    )
                )
            }
        }
        Spacer(Modifier.weight(1f))
    }
}

@Composable
fun BottomCounter(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Block_Prof)
    ) {
        Column (
            modifier = Modifier
                .padding(top = 34.dp, bottom = 32.dp, start = 20.dp, end = 20.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Сумма",
                    style = TextStyle(
                        fontFamily = newPeninimFontFamily,
                        fontSize = 16.sp,
                        color = SubTextDark_Prof
                    )
                )
                Text(
                    text = "₽753.95",
                    style = TextStyle(
                        fontFamily = newPeninimFontFamily,
                        fontSize = 16.sp,
                        color = SubTextDark_Prof
                    )
                )
            }
            Spacer(Modifier.height(10.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "Доставка",
                    style = TextStyle(
                        fontFamily = newPeninimFontFamily,
                        fontSize = 16.sp,
                        color = SubTextDark_Prof
                    )
                )
                Text(
                    text = "₽753.95",
                    style = TextStyle(
                        fontFamily = newPeninimFontFamily,
                        fontSize = 16.sp,
                        color = SubTextDark_Prof
                    )
                )
            }
            Spacer(Modifier.height(18.dp))
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = SubTextDark_Prof
            )
            Spacer(Modifier.height(15.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "Итого",
                    style = TextStyle(
                        fontFamily = newPeninimFontFamily,
                        fontSize = 16.sp,
                        color = Text_Prof
                    )
                )
                Text(
                    text = "₽753.95",
                    style = TextStyle(
                        fontFamily = newPeninimFontFamily,
                        fontSize = 16.sp,
                        color = Accent_Prof
                    )
                )
            }
            Spacer(Modifier.height(32.dp))
            Button(
                onClick = {

                },
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Accent_Prof,
                    contentColor = Block_Prof,
                    disabledContainerColor = Accent_Prof,
                    disabledContentColor = Block_Prof
                ),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(12.dp),
                    text = "Оформить заказ",
                    style = TextStyle(
                        fontFamily = newPeninimFontFamily,
                        fontSize = 14.sp,
                        color = Block_Prof
                    )
                )
            }
        }
    }
}