package edu.ucb.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import edu.ucb.project.exchangerate.data.dao.ExchangeRateDao
import edu.ucb.project.exchangerate.presentation.screen.ExchangeRateScreen
import edu.ucb.project.exchangerate.presentation.screen.ExchangeScreen
import edu.ucb.project.login.presentation.screen.LoginScreen
import edu.ucb.project.login.presentation.viewmodel.LoginViewModel
import edu.ucb.project.login.domain.usecase.LoginUseCase
import edu.ucb.project.login.domain.repository.LoginRepository
import edu.ucb.project.navigation.AppNavHost
import edu.ucb.project.user_search.presentation.screen.UserSearchScreen
import edu.ucb.project.weather.presentation.screen.WeatherScreen



@Composable
fun App(){
    MaterialTheme{
//        AppNavHost()
        ExchangeScreen()
    }
}

@Preview
@Composable
fun AppPreview(){
    org.koin.compose.KoinApplication(application = {
        modules(
            edu.ucb.project.di.presentationModule,
            edu.ucb.project.di.domainModule,
            edu.ucb.project.di.dataModule,
            org.koin.dsl.module {
                // Mocking the database DAO so it doesn't crash the preview
                single<edu.ucb.project.exchangerate.data.dao.ExchangeRateDao> {
                    object : edu.ucb.project.exchangerate.data.dao.ExchangeRateDao {
                        override suspend fun insert(dollar: edu.ucb.project.exchangerate.data.entity.ExchangeRateEntity) {}
                        override fun getList(): kotlinx.coroutines.flow.Flow<List<edu.ucb.project.exchangerate.data.entity.ExchangeRateEntity>> = kotlinx.coroutines.flow.flowOf(emptyList())
                        override suspend fun deleteAll() {}
                        override suspend fun insertDollars(lists: List<edu.ucb.project.exchangerate.data.entity.ExchangeRateEntity>) {}
                    }
                }
            }
        )
    }) {
        App()
    }
}




