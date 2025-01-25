package com.smtersoyoglu.cryptocurrencyapp.presentation.coin_list

import androidx.paging.PagingData
import com.smtersoyoglu.cryptocurrencyapp.domain.model.Coin
import kotlinx.coroutines.flow.Flow

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: Flow<PagingData<Coin>>? = null,
    val error: String = ""
)
