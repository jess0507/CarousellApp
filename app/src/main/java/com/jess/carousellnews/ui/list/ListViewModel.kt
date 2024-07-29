package com.jess.carousellnews.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.data.remote.Resource
import com.jess.data.remote.dto.News
import com.jess.data.repository.RemoteDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: RemoteDataRepository) :
    ViewModel() {
    var isLoading: Boolean = false
    var news by mutableStateOf(News())

    init {
        fetchLatestList()
    }

    fun fetchLatestList() {
        viewModelScope.launch {
            repository.fetchNews().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            news = it
                        }
                    }
                    is Resource.Error -> {

                    }
                    is Resource.Loading -> {
                        isLoading = result.isLoading
                    }
                }
            }
        }
    }
}