package com.smtersoyoglu.cryptocurrencyapp.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smtersoyoglu.cryptocurrencyapp.common.Resource
import com.smtersoyoglu.cryptocurrencyapp.domain.use_case.coin_main.GetAllCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getAllCoinsUseCase: GetAllCoinsUseCase,
) : ViewModel() {

    private var _uiState: MutableStateFlow<CoinListContract.UiState> =
        MutableStateFlow(CoinListContract.UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getCoins()
    }

    private fun getCoins() {
        getAllCoinsUseCase()
            .onStart {
                _uiState.update { it.copy(isLoading = true) }
            }
            .onCompletion {
                _uiState.update { it.copy(isLoading = false) }
            }
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update { it.copy(coins = resource.data ?: emptyList()) }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(error = resource.message ?: "An unexpected error occured")
                        }
                    }

                }
            }.launchIn(viewModelScope)
    }
}
