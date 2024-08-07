package com.sep.quiz.ui.screen.crypto.currency

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.quiz.ui.screen.crypto.currency.component.CurrencyListComponent
import com.sep.quiz.domain.entiry.crypto.CurrencyEntity
import com.sep.quiz.ui.designSystem.components.LoadingComponent
import com.sep.quiz.ui.utils.UiState

@Composable
fun CurrencyScreen() {
    val viewModel = hiltViewModel<CurrencyViewModel>()
    val currencyState = viewModel.currencyList.collectAsState()


    when (currencyState.value) {
        is UiState.Failed -> LoadingComponent()
        is UiState.Success -> CurrencyListComponent(currencyEntities = (currencyState.value as UiState.Success).data!!)
        is UiState.Loading -> LoadingComponent()
        is UiState.Initialize -> {}
    }
}

val mockCurrencyEntity = CurrencyEntity(
    currency = "currency",
    name = "name",
    fullName = "full name",
    precision = 24
)

@Preview
@Composable
private fun CurrencyScreenPreview() {
    CurrencyScreen()
}

@Preview
@Composable
private fun CurrencyListComponentPreview(modifier: Modifier = Modifier) {
    CurrencyListComponent(
        currencyEntities = listOf(
            mockCurrencyEntity,
            mockCurrencyEntity,
            mockCurrencyEntity,
            mockCurrencyEntity
        )
    )
}


