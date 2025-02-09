package com.example.pros_android.Presentation.Screens.LogIn

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pros_android.Data.User.UserState
import com.example.pros_android.R
import com.example.pros_android.ui.theme.Background_Prof
import com.example.pros_android.ui.theme.Hint_Prof
import com.example.pros_android.ui.theme.Red_Prof
import com.example.pros_android.ui.theme.SubTextDark_Prof
import com.example.pros_android.ui.theme.Text_Prof
import com.example.pros_android.ui.theme.newPeninimFontFamily
import com.example.pros_android.Presentation.Screens.Common.validateEmail
import com.example.pros_android.Presentation.ViewModel.AuthViewModel
import com.example.pros_android.ui.theme.Accent_Prof

@Composable
fun LogInScreen(navController: NavController, viewModel: AuthViewModel){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .systemBarsPadding()
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(Modifier.height(78.dp))
        Greeting()
        Spacer(Modifier.height(35.dp))
        LogInField(navController, viewModel)
        Spacer(Modifier.weight(1f))
        CreateAccount()
        Spacer(Modifier.height(50.dp))
    }
}

@Composable
fun Greeting(){
    Column {
        Text(
            text = "Привет!",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 32.sp,
                color = Text_Prof
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Заполните Свои Данные Или",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 16.sp,
                color = SubTextDark_Prof,
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Продолжите Через Социальные Медиа",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 16.sp,
                color = SubTextDark_Prof,
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun LogInField(navController: NavController, authViewModel: AuthViewModel){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false   ) }
    var isValidEmail by remember { mutableStateOf(true) }
    var isAlertVisible  by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val userState by authViewModel.userState
    var currentUserState by remember { mutableStateOf("") }

    LaunchedEffect (Unit){
        authViewModel.isUserLoggedIn(
            context
        )
    }

    val icon = if (passwordVisibility){
        painterResource(id = R.drawable.eye_open)
    } else {
        painterResource(id = R.drawable.eye_close)
    }

    var emailBorderColor by remember { mutableStateOf(Color.Transparent) }
    var passwordBorderColor by remember { mutableStateOf(Color.Transparent) }

    Column {
        Text(
            text = "Email",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 16.sp,
                color = Text_Prof
            )
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
                isValidEmail = validateEmail(it)
                emailBorderColor = if (!isValidEmail) Red_Prof else Color.Transparent
            },
            placeholder = { Text("xyz@gmail.com") },
            singleLine = true,
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Background_Prof,
                unfocusedContainerColor = Background_Prof,
                focusedIndicatorColor = emailBorderColor,
                unfocusedIndicatorColor = emailBorderColor,
                focusedLabelColor = Hint_Prof,
                unfocusedLabelColor = Hint_Prof,
                focusedTextColor = Hint_Prof,
                unfocusedTextColor = Hint_Prof,
                cursorColor = Hint_Prof
            ),
        )

        if (!isValidEmail) {
            Text(text = "Некорректный адрес электронной почты", color = Color.Red)
        }

        Spacer(Modifier.height(26.dp))
        Text(
            text = "Пароль",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 16.sp,
                color = Text_Prof
            )
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = password,
            onValueChange = {password = it},
            placeholder = { Text("Password") },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Background_Prof,
                unfocusedContainerColor = Background_Prof,
                focusedIndicatorColor = passwordBorderColor,
                unfocusedIndicatorColor = passwordBorderColor,
                focusedLabelColor = Hint_Prof,
                unfocusedLabelColor = Hint_Prof,
                focusedTextColor = Hint_Prof,
                unfocusedTextColor = Hint_Prof,
                cursorColor = Hint_Prof
            ),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = "Спрятать пароль"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Восстановить",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 12.sp,
                color = SubTextDark_Prof
            ),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                Log.e("IsValidEmail", "$isValidEmail")
                if (email.isBlank() || password.isBlank() || !isValidEmail){
                    emailBorderColor = Red_Prof
                    passwordBorderColor = Red_Prof
                } else {
                    authViewModel.logIn(
                        context = context,
                        userEmail = email,
                        userPassword = password
                    )
                    if (currentUserState == (userState as UserState.Success).msg){
                        navController.navigate("home")
                    }
                }
            },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Accent_Prof,
                contentColor = Text_Prof,
                disabledContainerColor = Accent_Prof,
                disabledContentColor = Text_Prof
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(14.dp),
                text = "Войти",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 14.sp,
                    color = Background_Prof
                )
            )
        }
        Spacer(Modifier.height(40.dp))
        Button(
            onClick = {
                authViewModel.logOut(context)
            },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Accent_Prof,
                contentColor = Text_Prof,
                disabledContainerColor = Accent_Prof,
                disabledContentColor = Text_Prof
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(14.dp),
                text = "Выйти",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 14.sp,
                    color = Background_Prof
                )
            )
        }
        Spacer(Modifier.height(40.dp))
        Button(
            onClick = {
                authViewModel.signUp(
                    context = context,
                    userEmail = email,
                    userPassword = password
                )
            },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Accent_Prof,
                contentColor = Text_Prof,
                disabledContainerColor = Accent_Prof,
                disabledContentColor = Text_Prof
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(14.dp),
                text = "Зарегать",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 14.sp,
                    color = Background_Prof
                )
            )
        }

        when(userState){
            is UserState.Loading -> {

            }
            is UserState.Success -> {
                val message = (userState as UserState.Success).msg
                currentUserState = message
            }
            is UserState.Error -> {
                val message = (userState as UserState.Error).msg
                emailBorderColor = Red_Prof
                passwordBorderColor = Red_Prof
                isAlertVisible = true
                currentUserState = message
            }
        }
        if (isAlertVisible){
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Пользователь не найден",
                style = TextStyle(
                    fontFamily = newPeninimFontFamily,
                    fontSize = 14.sp,
                    color = Red_Prof
                )
            )
        }

        if(currentUserState.isNotEmpty()){
            Text(text = currentUserState)
        }
        Log.e("CurrentUserState", currentUserState)

    }
}
@Composable
fun CreateAccount(){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = "Вы впервые?",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 16.sp,
                color = Hint_Prof
            )
        )
        Text(
            text = " Создать пользователя",
            style = TextStyle(
                fontFamily = newPeninimFontFamily,
                fontSize = 16.sp,
                color = Text_Prof,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}