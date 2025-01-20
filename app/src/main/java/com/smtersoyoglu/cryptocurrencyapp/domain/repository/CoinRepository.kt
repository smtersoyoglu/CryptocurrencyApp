package com.smtersoyoglu.cryptocurrencyapp.domain.repository

import com.smtersoyoglu.cryptocurrencyapp.common.Resource
import com.smtersoyoglu.cryptocurrencyapp.domain.model.Coin
import com.smtersoyoglu.cryptocurrencyapp.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoins(): Flow<Resource<List<Coin>>>

    suspend fun getCoinById(coinId: String): Resource<CoinDetail>

}