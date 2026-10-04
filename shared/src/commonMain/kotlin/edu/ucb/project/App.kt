package edu.ucb.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import edu.ucb.project.exchangerate.presentation.screen.ExchangeRateScreen
import edu.ucb.project.login.presentation.screen.LoginScreen
import edu.ucb.project.login.presentation.viewmodel.LoginViewModel
import edu.ucb.project.login.domain.usecase.LoginUseCase
import edu.ucb.project.login.domain.repository.LoginRepository
import edu.ucb.project.navigation.AppNavHost
import edu.ucb.project.user_search.presentation.screen.UserSearchScreen
import edu.ucb.project.weather.presentation.screen.WeatherScreen



@Composable
@Preview
fun App(){
    MaterialTheme{
//    AppNavHost()
        ExchangeRateScreen()
    }
}



