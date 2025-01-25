package com.smtersoyoglu.cryptocurrencyapp.presentation.coin_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.smtersoyoglu.cryptocurrencyapp.navigation.Screens
import com.smtersoyoglu.cryptocurrencyapp.presentation.coin_list.components.CoinListItem

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    // coins Flow'u null kontrolüyle LazyPagingItems'a bağlanır
    val coins = uiState.coins?.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            // CircularProgressIndicator 'isLoading' ve coins'in yüklenme durumlarına bağlı
            uiState.isLoading || coins?.loadState?.refresh is LoadState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            uiState.error.isNotEmpty() -> {
                Text(
                    text = uiState.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
            coins != null -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp, bottom = 40.dp)
                ) {
                    items(coins.itemCount) { index ->
                        val coin = coins[index]
                        coin?.let {
                            CoinListItem(
                                coin = it,
                                onItemClick = {
                                    navController.navigate(Screens.CoinDetailScreen(it.id))
                                }
                            )
                        }
                    }

                    // Hata durumu
                    if (coins.loadState.refresh is LoadState.Error) {
                        val error = (coins.loadState.refresh as LoadState.Error).error
                        item {
                            Text(
                                text = error.localizedMessage ?: "An error occurred",
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                            )
                        }
                    }

                    // Sayfa ekleme sırasında hata
                    if (coins.loadState.append is LoadState.Error) {
                        val error = (coins.loadState.append as LoadState.Error).error
                        item {
                            Text(
                                text = error.localizedMessage
                                    ?: "An error occurred while loading more data",
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                            )
                        }
                    }
                }
                // Daha fazla veri yükleniyor durumu
                if (coins.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.BottomCenter))
                }

            }
            else -> {
                Text(
                    text = "No data available.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    }
}





