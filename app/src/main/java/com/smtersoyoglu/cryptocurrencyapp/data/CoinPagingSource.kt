package com.smtersoyoglu.cryptocurrencyapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.smtersoyoglu.cryptocurrencyapp.data.mappers.toCoin
import com.smtersoyoglu.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.smtersoyoglu.cryptocurrencyapp.domain.model.Coin
import javax.inject.Inject

class CoinPagingSource @Inject constructor(
    private val api: CoinPaprikaApi
) : PagingSource<Int, Coin>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        return try {
            val currentPage = params.key ?: 1
            val pageSize = params.loadSize // PagingConfig'teki değeri alır
            val response = api.getCoins(page = currentPage)

            val startIndex = (currentPage - 1) * pageSize
            val endIndex = startIndex + pageSize

            val coins = if (response.size > startIndex) {
                response.subList(startIndex, minOf(endIndex, response.size)).map { it.toCoin() }
            } else {
                emptyList()
            }

            LoadResult.Page(
                data = coins,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (coins.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}




