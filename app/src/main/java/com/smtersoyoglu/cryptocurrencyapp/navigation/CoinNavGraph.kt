package com.smtersoyoglu.cryptocurrencyapp.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smtersoyoglu.cryptocurrencyapp.presentation.coin_list.CoinListScreen
import com.smtersoyoglu.cryptocurrencyapp.presentation.theme.BackgroundColor

@Composable
fun CoinNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.CoinListScreen,
        modifier = modifier.background(BackgroundColor)

    ) {

        composable<Screens.CoinListScreen> {
            CoinListScreen(navController = navController)
        }






    }
}