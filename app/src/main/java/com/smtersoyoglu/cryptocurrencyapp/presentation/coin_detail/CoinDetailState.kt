package com.smtersoyoglu.cryptocurrencyapp.presentation.coin_detail

import com.smtersoyoglu.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = "",
)
