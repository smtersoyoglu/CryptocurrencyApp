package com.smtersoyoglu.cryptocurrencyapp.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.smtersoyoglu.cryptocurrencyapp.common.Resource
import com.smtersoyoglu.cryptocurrencyapp.domain.use_case.coin_detail.GetCoinDetailByIdUseCase
import com.smtersoyoglu.cryptocurrencyapp.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailByIdUseCase: GetCoinDetailByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoinDetailState())
    val uiState: StateFlow<CoinDetailState> = _uiState.asStateFlow()

    private val args = savedStateHandle.toRoute<Screens.CoinDetailScreen>()

    init {
        fetchCoinById(args.coinId)
    }

    private fun fetchCoinById(coinId: String) {
        getCoinDetailByIdUseCase(coinId)
            .onStart {
                _uiState.update { it.copy(isLoading = true) }
            }
            .onCompletion {
                _uiState.update { it.copy(isLoading = false) }

            }
            .onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update { it.copy(coinDetail = resource.data) }

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