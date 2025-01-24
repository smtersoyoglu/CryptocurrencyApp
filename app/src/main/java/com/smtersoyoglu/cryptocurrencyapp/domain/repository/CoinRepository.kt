package com.smtersoyoglu.cryptocurrencyapp.domain.repository

import androidx.paging.PagingData
import com.smtersoyoglu.cryptocurrencyapp.common.Resource
import com.smtersoyoglu.cryptocurrencyapp.domain.model.Coin
import com.smtersoyoglu.cryptocurrencyapp.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<PagingData<Coin>>

    fun getCoinById(coinId: String): Flow<Resource<CoinDetail>>

}