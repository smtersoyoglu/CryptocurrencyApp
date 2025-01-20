package com.smtersoyoglu.cryptocurrencyapp.presentation.coin_list

import com.smtersoyoglu.cryptocurrencyapp.domain.model.Coin

object CoinListContract {

    data class UiState(
        val isLoading: Boolean = false,
        val coins: List<Coin> = emptyList(),
        val error: String = "",
        )

    sealed class UiAction {
        data class CoinClicked(val coin: Coin) : UiAction()

    }

    sealed class UiEffect {
        data object NavigateToCoinDetail : UiEffect()
    }

}