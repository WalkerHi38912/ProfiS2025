package com.example.pros_android.OnBoard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Block_Prof
import com.example.pros_android.ui.theme.newPeninimFontFamily
import kotlinx.coroutines.delay

@Composable
fun Onboard1(){

    Box{
        Column {
            Spacer(Modifier.height(29.dp))
            Text(
                text = "ДОБРО",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 30.sp,
                    color = Block_Prof
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "ПОЖАЛОВАТЬ",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 30.sp,
                    color = Block_Prof
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(130.dp))
            Image(
                painter = painterResource(id = R.drawable.image_1_onboard),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(375.dp)
            )
        }
    }
}