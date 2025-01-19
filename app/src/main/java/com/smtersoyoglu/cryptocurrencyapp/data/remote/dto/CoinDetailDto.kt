package com.smtersoyoglu.cryptocurrencyapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    val description: String,//Used
    @SerializedName("development_status") val developmentStatus: String,
    @SerializedName("first_data_at") val firstDataAt: String,
    @SerializedName("hardware_wallet") val hardwareWallet: Boolean,
    @SerializedName("hash_algorithm") val hashAlgorithm: String,
    val id: String,//Used
    @SerializedName("is_active") val isActive: Boolean,//Used
    @SerializedName("is_new") val isNew: Boolean,
    @SerializedName("last_data_at") val lastDataaT: String,
    val links: Links,
    @SerializedName("links_extended") val linkExtended: List<LinksExtended>,
    val logo: String,
    val message: String,
    val name: String,//Used
    @SerializedName("open_source") val openSource: Boolean,
    @SerializedName("org_structure") val orgStructure: String,
    @SerializedName("proof_type") val proofType: String,
    val rank: Int,//Used
    @SerializedName("started_at") val startedAt: String,
    val symbol: String,//Used
    val tags: List<Tag>,//Used
    val team: List<TeamMember>,//Used
    val type: String,
    val whitepaper: Whitepaper
)