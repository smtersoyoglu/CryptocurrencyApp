package com.smtersoyoglu.cryptocurrencyapp.data.mappers

import com.smtersoyoglu.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.smtersoyoglu.cryptocurrencyapp.data.remote.dto.CoinDto
import com.smtersoyoglu.cryptocurrencyapp.domain.model.Coin
import com.smtersoyoglu.cryptocurrencyapp.domain.model.CoinDetail

fun CoinDto.toCoin(): Coin = Coin(
    id = id,
    isActive = isActive,
    name = name,
    rank = rank,
    symbol = symbol,
)

fun CoinDetailDto.toCoinDetail(): CoinDetail = CoinDetail(
    coinId = id,
    name = name,
    description = description,
    symbol = symbol,
    rank = rank,
    isActive = isActive,
    tags = tags.map { it.name },
    team = team
)
