package com.smtersoyoglu.cryptocurrencyapp.domain.use_case.coin_detail

import com.smtersoyoglu.cryptocurrencyapp.common.Resource
import com.smtersoyoglu.cryptocurrencyapp.domain.model.CoinDetail
import com.smtersoyoglu.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinDetailByIdUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> {
        return coinRepository.getCoinById(coinId)
    }
}