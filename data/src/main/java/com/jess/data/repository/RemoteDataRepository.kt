package com.jess.data.repository

import com.jess.data.remote.Resource
import com.jess.data.remote.dto.News
import kotlinx.coroutines.flow.Flow

interface RemoteDataRepository {
    suspend fun fetchNews(): Flow<Resource<News>>
}