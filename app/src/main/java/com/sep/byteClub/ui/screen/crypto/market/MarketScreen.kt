package com.sep.byteClub.ui.screen.crypto.market

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.sep.byteClub.ui.designSystem.components.LoadingComponent
import com.sep.byteClub.ui.utils.UiState

@Composable
fun MarketScreen() {
    val viewModel = hiltViewModel<MarketViewModel>()
    val marketListState = viewModel.marketList.collectAsState()

    when (marketListState.value) {
        is UiState.Failed -> LoadingComponent()
        is UiState.Success -> MarketListComponent(marketList = (marketListState.value as UiState.Success).data!!)
        is UiState.Loading -> LoadingComponent()
        is UiState.Initialize -> {

        }
    }
}