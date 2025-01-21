package com.smtersoyoglu.cryptocurrencyapp.data.repository

import com.smtersoyoglu.cryptocurrencyapp.common.Resource
import com.smtersoyoglu.cryptocurrencyapp.data.mappers.toCoin
import com.smtersoyoglu.cryptocurrencyapp.data.mappers.toCoinDetail
import com.smtersoyoglu.cryptocurrencyapp.data.network.safeFlow
import com.smtersoyoglu.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.smtersoyoglu.cryptocurrencyapp.domain.model.Coin
import com.smtersoyoglu.cryptocurrencyapp.domain.model.CoinDetail
import com.smtersoyoglu.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinPaprikaApi: CoinPaprikaApi,
) : CoinRepository {

    override fun getCoins(): Flow<Resource<List<Coin>>> = safeFlow {
        coinPaprikaApi.getCoins().map { it.toCoin() }
    }


    override fun getCoinById(coinId: String): Flow<Resource<CoinDetail>> = safeFlow {
        coinPaprikaApi.getCoinById(coinId).toCoinDetail()
    }
}