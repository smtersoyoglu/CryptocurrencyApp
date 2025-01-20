package com.smtersoyoglu.cryptocurrencyapp.data.repository

import com.smtersoyoglu.cryptocurrencyapp.common.Resource
import com.smtersoyoglu.cryptocurrencyapp.data.mappers.toCoin
import com.smtersoyoglu.cryptocurrencyapp.data.mappers.toCoinDetail
import com.smtersoyoglu.cryptocurrencyapp.data.network.safeCall
import com.smtersoyoglu.cryptocurrencyapp.data.network.safeFlow
import com.smtersoyoglu.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.smtersoyoglu.cryptocurrencyapp.domain.model.Coin
import com.smtersoyoglu.cryptocurrencyapp.domain.model.CoinDetail
import com.smtersoyoglu.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinPaprikaApi: CoinPaprikaApi,
    //private val mapper: CoinMapper
) : CoinRepository {

    override fun getCoins(): Flow<Resource<List<Coin>>> = safeFlow{
        coinPaprikaApi.getCoins().map { it.toCoin() }
    }


    override suspend fun getCoinById(coinId: String): Resource<CoinDetail> = safeCall {
        coinPaprikaApi.getCoinById(coinId).toCoinDetail()
    }
}