package edu.ucb.project.signin.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import kotlinfirstproject.shared.generated.resources.Res
import kotlinfirstproject.shared.generated.resources.signin_button
import kotlinfirstproject.shared.generated.resources.signin_title

@Composable
fun SigninPage() {
    var userSignIn by remember { mutableStateOf("") }
    var passwordSignIn by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(Res.string.signin_title),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        //Email
        OutlinedTextField(
            value = userSignIn,
            onValueChange = { userSignIn = it },
            placeholder = { Text(text = "Email Address", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Password
        OutlinedTextField(
            value = passwordSignIn,
            onValueChange = { passwordSignIn = it },
            placeholder = { Text(text = "Password", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CB07D),
                contentColor = Color.White
            ),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = stringResource(Res.string.signin_button),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun SigninPagePreview() {
    MaterialTheme{
        SigninPage()
    }

}