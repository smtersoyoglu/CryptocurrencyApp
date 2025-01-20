package com.smtersoyoglu.cryptocurrencyapp.domain.use_case.coin_main

import com.smtersoyoglu.cryptocurrencyapp.common.Resource
import com.smtersoyoglu.cryptocurrencyapp.domain.model.Coin
import com.smtersoyoglu.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> {
        return coinRepository.getCoins()
    }
}