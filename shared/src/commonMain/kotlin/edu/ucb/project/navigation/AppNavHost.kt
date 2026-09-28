package edu.ucb.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucb.project.login.presentation.screen.LoginScreen
import edu.ucb.project.user_search.presentation.screen.UserSearchScreen

@Composable
fun AppNavHost(){
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = NavRoute.LogIn) {
        composable<NavRoute.UserSearch> {
            UserSearchScreen()
        }

        composable<NavRoute.LogIn> {
            LoginScreen(navController)
        }
    }

}