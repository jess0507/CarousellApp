package com.jess.carousellnews.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jess.data.remote.dto.NewsItem

@Composable
fun ListScreenWithViewModel(
    viewModel: ListViewModel = hiltViewModel()
) {
    val news = viewModel.news
    ListScreen(isLoading = viewModel.isLoading, news = news)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    isLoading: Boolean,
    news: List<NewsItem>,
) {
    var sortByRecent by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                windowInsets = WindowInsets(0.dp),
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = {
                    Text("Carousell News")
                },
                actions = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem({ Text(text = "Recent") }, onClick = { sortByRecent = true })
                        DropdownMenuItem({ Text(text = "Popular") }, onClick = { sortByRecent = false })
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(0.dp),
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            val sortedItems = if (sortByRecent) {
                news.sortedByDescending { it.time_created }
            } else {
                news.sortedByDescending { it.rank }
            }.toList()
            NewsListView(
                isLoading = isLoading,
                news = sortedItems,
            )
        }
    }
}