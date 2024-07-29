package com.jess.data.remote

import com.jess.data.remote.dto.News
import retrofit2.http.GET

interface RemoteApi {
    @GET("carousell-interview-assets/android/carousell_news.json")
    suspend fun getCarousellNews(
    ): News

    companion object {
        const val BASE_URL = "https://storage.googleapis.com"
    }
}
