package com.smtersoyoglu.cryptocurrencyapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Links(
    val explorer: List<String>,
    val facebook: List<String>,
    val reddit: List<String>,
    @SerializedName("source_code")
    val source_code: List<String>,
    val website: List<String>,
    val youtube: List<String>
)