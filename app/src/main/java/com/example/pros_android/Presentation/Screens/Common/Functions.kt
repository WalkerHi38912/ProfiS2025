package com.example.pros_android.Presentation.Screens.Common

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pros_android.Data.User.model.Product
import com.example.pros_android.Presentation.ViewModel.AuthViewModel
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Accent_Prof
import com.example.pros_android.ui.theme.Background_Prof
import com.example.pros_android.ui.theme.Block_Prof
import com.example.pros_android.ui.theme.Hint_Prof
import com.example.pros_android.ui.theme.SubTextLight_Prof
import com.example.pros_android.ui.theme.Text_Prof
import com.example.pros_android.ui.theme.newPeninimFontFamily

fun validateEmail(email: String): Boolean {
    val regex = "^[a-z0-9]+([._%+-]?[a-z0-9]+)*@[a-z0-9]+\\.[a-z]{2,}$".toRegex()
    return regex.matches(email)
}
@Composable
fun ProductCard(product: Product){
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .height(200.dp)
            .background(Block_Prof)
            .padding(start = 9.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 9.dp)
                //.background(Color.Yellow)
        ){
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(top = 9.dp, bottom = 9.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .size(27.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.favorite),
                    contentDescription = null
                )
            }
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .padding(top = 18.dp, bottom = 12.dp)
                    .align(Alignment.Center)
                    //.background(Color.Green)
                    .height(80.dp)
                    .width(142.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.weight(1f))
        Text(
            text = product.name,
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 16.sp,
                color = Hint_Prof
            )
        )
        Spacer(Modifier.height(8.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = "${product.price}",
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
fun Categories(authViewModel: AuthViewModel, selectedCategory: Int, onCategorySelected: (Int) -> Unit){
    val itemsList = mapOf(0 to "All", 1 to "Tennis", 2 to "Running", 3 to "Classic")
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
            items(itemsList.toList()){ item ->
                Button(
                    onClick = {
                        onCategorySelected(item.first)
                        authViewModel.getCollection(item.first)
                              },
                    modifier = Modifier
                        .height(40.dp)
                        .width(108.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Block_Prof
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = item.second,
                        style = TextStyle(
                            fontFamily = newPeninimFontFamily,
                            fontSize = 12.sp,
                            color = Text_Prof
                        )
                    )
                }
            }
        }
    }

}

@ExperimentalMaterial3Api
@Composable
fun Dialog(showDialog: Boolean, errorText: String,  onDismiss: () -> Unit){
    if(showDialog){
        BasicAlertDialog(
            onDismissRequest = onDismiss
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(20.dp),
            ) {
                Column {
                    Text(
                        modifier = Modifier
                            .padding(14.dp),
                        text = "Ошибка",
                        style = TextStyle(
                            fontFamily = newPeninimFontFamily,
                            fontSize = 16.sp,
                            color = Text_Prof,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        modifier = Modifier
                            .padding(14.dp),
                        text = errorText,
                        style = TextStyle(
                            fontFamily = newPeninimFontFamily,
                            fontSize = 14.sp,
                            color = Text_Prof
                        )
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        TextButton(onClick = onDismiss) {
                            Text("ОК")
                        }
                    }
                }
            }
        }
    }
}

fun getErrorMessage(errorText: String?): String {
    return when {
        errorText.isNullOrBlank() -> "Неизвестная ошибка"

        errorText.contains("Invalid login credentials", ignoreCase = true) -> "Пользователь не найден: Неправильный логин или пароль"
        errorText.contains("USER_NOT_FOUND", ignoreCase = true) -> "Пользователь не найден"
        errorText.contains("HTTP request to http://localhost (GET) failed with message: Connection reset by peer", ignoreCase = true) -> "Связь с сревером потеряна..."
        errorText.contains("HTTP request to http://localhost (POST) failed with message", ignoreCase = true) -> "Ошибка сети. Проверьте интернет-соединение"

        else -> "Что-то пошло не так. Попробуйте позже"
    }
}

@ExperimentalMaterial3Api
@Composable
fun LoadingDialog(showDialog: Boolean, onDismiss: () -> Unit) {
    if (showDialog) {
        BasicAlertDialog(onDismissRequest = onDismiss) {
            Box(
                modifier = Modifier
                    .background(color = Color.Transparent, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        color = Accent_Prof,
                        strokeWidth = 4.dp
                    )
                }
            }
        }
    }
}

@Composable
fun ShowToast(message: String) {
    val context = LocalContext.current
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}