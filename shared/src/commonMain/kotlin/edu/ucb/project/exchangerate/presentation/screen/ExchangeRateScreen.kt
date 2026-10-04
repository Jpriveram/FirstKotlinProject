package edu.ucb.project.exchangerate.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import edu.ucb.project.exchangerate.presentation.viewmodel.ExchangeRateEvent
import edu.ucb.project.exchangerate.presentation.viewmodel.ExchangeRateViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExchangeRateScreen(
    viewModel: ExchangeRateViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()
    Column {
        Button(onClick = {
            viewModel.emitEvent(ExchangeRateEvent.OnAddRecord)
        }) {
            Text("Add")
        }
        Text(state.value.list.size.toString())
    }

}