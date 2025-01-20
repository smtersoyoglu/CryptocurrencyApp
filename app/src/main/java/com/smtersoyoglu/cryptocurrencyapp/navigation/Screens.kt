package com.smtersoyoglu.cryptocurrencyapp.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {

    @Serializable
    data object CoinListScreen : Screens

    @Serializable
    data class CoinDetailScreen(val coinId: String) : Screens

}