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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Block_Prof
import com.example.pros_android.ui.theme.SubTextLight_Prof
import com.example.pros_android.ui.theme.newPeninimFontFamily
import kotlinx.coroutines.delay

@Composable
fun Onboard2And3(imageResId: Int, OnbardId: Int){
    var imageResId: Int = 0
    var Header1: String = ""
    var Header2: String = ""
    var Sub1: String = ""
    var Sub2: String = ""

    if (OnbardId == 3){
        imageResId = R.drawable.image_3_onboard
        Header1 = stringResource(R.string.onboard3_Header1)
        Header2 = stringResource(R.string.onboard3_Headre2)
        Sub1 = stringResource(R.string.onboard3_SubText1)
        Sub2 = stringResource(R.string.onboard3_SubText2)
    } else if (OnbardId == 2) {
        imageResId = R.drawable.image_2_onboard
        Header1 = stringResource(R.string.onboard2_Header1)
        Header2 = stringResource(R.string.onboard2_Headre2)
        Sub1 = stringResource(R.string.onboard2_SubText1)
        Sub2 = stringResource(R.string.onboard2_SubText2)
    }

    Box{
        Column {
            Spacer(Modifier.height(37.dp))
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(375.dp)
            )
            Spacer(Modifier.height(60.dp))
            Text(
                text = Header1,
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 34.sp,
                    color = Block_Prof
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = Header2,
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 34.sp,
                    color = Block_Prof
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = Sub1,
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 16.sp,
                    color = SubTextLight_Prof
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = Sub2,
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 16.sp,
                    color = SubTextLight_Prof
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}