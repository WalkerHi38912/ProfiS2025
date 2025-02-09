package com.example.pros_android.Presentation.Screens.OnBoard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Accent_Prof
import com.example.pros_android.ui.theme.Block_Prof
import com.example.pros_android.ui.theme.Disable_Prof
import com.example.pros_android.ui.theme.Text_Prof
import com.example.pros_android.ui.theme.newPeninimFontFamily
import kotlinx.coroutines.delay

@Composable
fun OnboardMain(navController: NavController) {
    var currentOnboard by remember { mutableStateOf(0) }
    var currentButtonText by remember { mutableStateOf("Начать") }
    var imageVisible by remember { mutableStateOf(false) }
    val gradientColors = if (currentOnboard == 0){
        listOf(Color(0xFF48B2E7), Color(0xFF44A9DC), Color(0xFF2B6B8B))
    } else {
        listOf(Color(0xFF48B2E7), Color(0xFF2B6B8B))
    }

    LaunchedEffect(Unit) {
        delay(100)  // Задержка для анимации первого экрана
        imageVisible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors,
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize()
        ) {
            Crossfade(
                targetState = imageVisible,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = FastOutSlowInEasing
                )
            ) { visible ->
                if (visible) {
                    Crossfade(
                        targetState = currentOnboard,
                        animationSpec = tween(
                            durationMillis = 800,
                            easing = FastOutSlowInEasing
                        )
                    ) { screenIndex ->
                        when (screenIndex) {
                            0 -> Onboard1()
                            1 -> Onboard2And3(imageResId = R.drawable.image_2_onboard, OnbardId = 2)
                            2 -> Onboard2And3(imageResId = R.drawable.image_3_onboard, OnbardId = 3)
                        }
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            Spacer(Modifier.weight(1f))
            Box{
                Column {
                    Spacer(Modifier.height(if (currentOnboard == 0) 26.dp else 40.dp))
                    Box{
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            for (i in 0..2) {
                                val isSelected = i == currentOnboard
                                Box(
                                    modifier = Modifier
                                        .padding(start = 12.dp, end = 12.dp)
                                        .height(5.dp)
                                        .width(if (isSelected) 43.dp else 28.dp)
                                        .background(
                                            color = if (isSelected) Block_Prof else Disable_Prof,
                                            shape = androidx.compose.foundation.shape.CircleShape
                                        )
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(if (currentOnboard == 0) 136.dp else 95.dp))
                    Button(
                        onClick = {
                            if (currentOnboard != 2) {
                                currentOnboard++
                                if (currentOnboard == 1) {
                                    currentButtonText = "Далее"
                                }
                            } else {
                                navController.navigate("home")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Block_Prof,
                            contentColor = Text_Prof,
                            disabledContainerColor = Block_Prof,
                            disabledContentColor = Text_Prof
                        ),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(14.dp),
                            text = currentButtonText,
                            style = TextStyle(
                                fontFamily = newPeninimFontFamily,
                                fontSize = 14.sp,
                                color = Text_Prof
                            )
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}
