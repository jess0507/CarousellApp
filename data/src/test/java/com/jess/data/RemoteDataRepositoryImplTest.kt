package com.jess.data

import com.jess.data.remote.RemoteApi
import com.jess.data.remote.Resource
import com.jess.data.repository.RemoteDataRepositoryImpl
import com.jess.data.test.FakeSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RemoteDataRepositoryImplTest {
    private val api = mockk<RemoteApi>()
    private val repository = RemoteDataRepositoryImpl(api)

    @Test
    fun fetchNews_success() = runTest {
        coEvery { api.getCarousellNews() } returns FakeSource.news
        val results = runBlocking { repository.fetchNews().toList() }

        assertEquals(2, results.size)
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Success)
        assertEquals(FakeSource.news, results[1].data)
    }

    @Test
    fun fetchNews_error() = runTest {
        coEvery { api.getCarousellNews() } throws IOException("Network error")
        val results = runBlocking { repository.fetchNews().toList() }

        assertEquals(2, results.size)
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Error)
        assertEquals("Network error", (results[1] as Resource.Error).message)
    }

    @Test
    fun fetchNews_error_httpException() = runTest {
        val errorResponse = Response.error<Any>(500, "Internal Server Error".toResponseBody(null))

        coEvery { api.getCarousellNews() } throws HttpException(errorResponse)
        val results = runBlocking { repository.fetchNews().toList() }

        assertEquals(2, results.size)
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Error)
        assertEquals("HTTP 500 Response.error()", (results[1] as Resource.Error).message)
    }
}