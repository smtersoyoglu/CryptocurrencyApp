package com.smtersoyoglu.cryptocurrencyapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: String,//Used
    @SerializedName("is_active") val isActive: Boolean,//Used
    @SerializedName("is_new") val isNew: Boolean,//Not Needed
    val name: String,//Used
    val rank: Int, //Used
    val symbol: String,//Used
    val type: String, // Not Needed
)