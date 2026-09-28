package edu.ucb.project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.project.login.presentation.viewmodel.LoginEvent
import edu.ucb.project.login.presentation.viewmodel.LoginState

@Composable
fun SigninPage(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // EMAIL
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.username,
            onValueChange = {
                onEvent(LoginEvent.OnUsernameChanged(it))
            },
            label = {
                Text("Email")
            },
            placeholder = {
                Text("example@email.com")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // PASSWORD
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            onValueChange = {
                onEvent(LoginEvent.OnPasswordChanged(it))
            },
            label = {
                Text("Password")
            },
            placeholder = {
                Text("Password")
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // LOGIN BUTTON
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            onClick = {
                onEvent(LoginEvent.OnLoginClick)
            }
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text("Sign In")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SigninPagePreview() {
    SigninPage(
        state = LoginState(),
        onEvent = {}
    )
}