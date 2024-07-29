package com.jess.data.repository

import com.jess.data.remote.RemoteApi
import com.jess.data.remote.Resource
import com.jess.data.remote.dto.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
    private val api: RemoteApi,
) : RemoteDataRepository {

    override suspend fun fetchNews(): Flow<Resource<News>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = api.getCarousellNews()
                println("Fetched response: $response")
                emit(Resource.Success(data = response))
            } catch (e: IOException) {
                emit(Resource.Error(message = e.message))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.message))
            }
        }
    }
}