package com.smtersoyoglu.cryptocurrencyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.smtersoyoglu.cryptocurrencyapp.common.Constants.PAGE_SIZE
import com.smtersoyoglu.cryptocurrencyapp.common.Resource
import com.smtersoyoglu.cryptocurrencyapp.data.CoinPagingSource
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

    override fun getCoins(): Flow<PagingData<Coin>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CoinPagingSource(coinPaprikaApi) }
        ).flow
    }


    override fun getCoinById(coinId: String): Flow<Resource<CoinDetail>> = safeFlow {
        coinPaprikaApi.getCoinById(coinId).toCoinDetail()
    }

}