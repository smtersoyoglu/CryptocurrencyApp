package com.smtersoyoglu.cryptocurrencyapp.data.network

import android.util.Log
import com.smtersoyoglu.cryptocurrencyapp.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

fun <T> safeFlow(action: suspend () -> T): Flow<Resource<T>> = flow {
    try {
        val result = action()
        emit(Resource.Success(result))
    } catch (e: IOException) {
        Log.e("SafeFlow", "Network error: ${e.message}")
        emit(Resource.Error("Network error: ${e.message}"))
    } catch (e: HttpException) {
        Log.e("SafeFlow", "API error: ${e.message}")
        emit(Resource.Error("API error: ${e.message}"))
    } catch (e: Exception) {
        Log.e("SafeFlow", "Unexpected error: ${e.message}")
        emit(Resource.Error("Unexpected error: ${e.message}"))
    }
}
