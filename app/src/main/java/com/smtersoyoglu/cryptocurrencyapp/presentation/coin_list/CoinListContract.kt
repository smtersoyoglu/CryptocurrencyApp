package com.smtersoyoglu.cryptocurrencyapp.presentation.coin_list

import androidx.paging.PagingData
import com.smtersoyoglu.cryptocurrencyapp.domain.model.Coin
import kotlinx.coroutines.flow.Flow

object CoinListContract {

    data class UiState(
        val isLoading: Boolean = false,
        val coins: Flow<PagingData<Coin>>? = null,
        val error: String = ""
    )

    sealed class UiAction {
        data class CoinClicked(val coin: Coin) : UiAction()

    }

    sealed class UiEffect {
        data object NavigateToCoinDetail : UiEffect()
    }

}