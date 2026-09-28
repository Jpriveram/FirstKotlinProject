package edu.ucb.project.weather.presentation.viewmodel

import edu.ucb.project.user_search.presentation.viewmodel.UserSearchEffects

sealed interface WeatherEffects {
    data class ShowToast(val message: String): WeatherEffects
    data object NavigateToBack : WeatherEffects
}