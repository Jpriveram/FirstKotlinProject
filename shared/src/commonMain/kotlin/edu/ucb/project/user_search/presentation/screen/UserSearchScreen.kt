package edu.ucb.project.user_search.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import org.koin.compose.viewmodel.koinViewModel
import edu.ucb.project.user_search.presentation.viewmodel.UserSearchEffects
import edu.ucb.project.user_search.presentation.viewmodel.UserSearchEvents
import edu.ucb.project.user_search.presentation.viewmodel.UserSearchViewModel

@Composable
fun UserSearchScreen( viewModel: UserSearchViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when(effect) {
                is UserSearchEffects.ShowToast -> {
                    //Todo
                }

                UserSearchEffects.NavigateToBack -> {
                    //Todo
                }
            }
        }
    }

    Column {
        TextField(value = state.value.alias, onValueChange = {
            viewModel.emitEvent(UserSearchEvents.OnAliasChange(it))
        })
        Button(onClick = {
            viewModel.emitEvent(UserSearchEvents.OnSubmit)
        }) {
            Text("Buscar")
        }
        state.value.email?.let {
            Text(it)
        }
        state.value.avatarUrl?.let{
            Text(it)
        }
    }
}