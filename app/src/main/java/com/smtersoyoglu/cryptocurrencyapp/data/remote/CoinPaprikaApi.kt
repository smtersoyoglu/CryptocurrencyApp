package com.smtersoyoglu.cryptocurrencyapp.data.remote

import com.smtersoyoglu.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.smtersoyoglu.cryptocurrencyapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinPaprikaApi {

    //GetAllCoins
    @GET("/v1/coins")
    suspend fun getCoins(
        @Query("page") page: Int,
    ): List<CoinDto>

    //GetCoinById
    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

}