package com.smtersoyoglu.cryptocurrencyapp.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.smtersoyoglu.cryptocurrencyapp.domain.use_case.coin_main.GetAllCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getAllCoinsUseCase: GetAllCoinsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoinListState())
    val uiState: StateFlow<CoinListState> = _uiState.asStateFlow()

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val flow = getAllCoinsUseCase().cachedIn(viewModelScope)
                _uiState.update { it.copy(coins = flow, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message ?: "Unknown Error")
                }
            }
        }
    }
}





