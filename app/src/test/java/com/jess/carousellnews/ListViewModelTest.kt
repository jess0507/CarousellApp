package com.jess.carousellnews

import com.jess.carousellnews.ui.list.ListViewModel
import com.jess.data.remote.Resource
import com.jess.data.repository.RemoteDataRepository
import com.jess.data.test.FakeSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val repository = mockk<RemoteDataRepository>(relaxed = true) // relaxed = true
    private lateinit var viewModel: ListViewModel

    @Before
    fun setUp() {
        viewModel = ListViewModel(repository)
    }

    @Test
    fun fetchNews_success() = runTest {
        coEvery { repository.fetchNews() } returns flow {
            emit(Resource.Loading())
            emit(Resource.Success(FakeSource.news))
        }
        viewModel.fetchLatestList()

        advanceUntilIdle()
        assertTrue(viewModel.isLoading)
        assertEquals(FakeSource.news, viewModel.news)
    }

    @Test
    fun fetchNews_error() = runTest {
        coEvery { repository.fetchNews() } returns flow {
            emit(Resource.Loading())
            emit(Resource.Error(message = "Network error"))
        }
        viewModel.fetchLatestList()

        advanceUntilIdle()
        assertTrue(viewModel.isLoading)
        assertEquals(0, viewModel.news.size)
    }
}