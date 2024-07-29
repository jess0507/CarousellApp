package com.jess.data.remote.dto

data class NewsItem(
    val id: Int,
    val banner_url: String,
    val description: String,
    val rank: Int,
    val time_created: Long,
    val title: String
)