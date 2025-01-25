package com.smtersoyoglu.cryptocurrencyapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smtersoyoglu.cryptocurrencyapp.data.remote.dto.TeamMember
import com.smtersoyoglu.cryptocurrencyapp.presentation.theme.CoinTextColor

@Composable
fun TeamListItem(
    teamMember: TeamMember,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = teamMember.name,
            fontWeight = FontWeight.Medium,
            color = CoinTextColor,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = teamMember.position,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = CoinTextColor,
            fontStyle = FontStyle.Italic
        )
    }
}