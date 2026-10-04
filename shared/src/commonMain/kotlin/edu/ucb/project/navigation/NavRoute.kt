package edu.ucb.project.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {

    @Serializable
    object UserSearch: NavRoute()

    @Serializable
    object LogIn: NavRoute()

    @Serializable
    object ExchangeRateList: NavRoute()

    @Serializable
    object ExchangeRateAdd: NavRoute()
}
