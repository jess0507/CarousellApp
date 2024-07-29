package com.jess.carousellnews.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jess.data.remote.dto.News
import com.jess.data.remote.dto.NewsItem

@Composable
fun NewsListView(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    news: List<NewsItem>,
) {
    Box(
        modifier = modifier.fillMaxSize() // Ensure Box takes up full size
    ) {
    if (isLoading && news.isEmpty()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(news.size) { index ->
                Item(item = news[index])
                if (index < news.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
        }
}

@Preview
@Composable
fun ListScreenPreview() {
    var items = News().apply {
        add(
            NewsItem(
                id= 121,
                title= "Carousell is launching its own digital wallet to improve payments for its users",
                description= "Due to launch next month in Singapore, CarouPay will allow buyers and sellers to complete transactions without leaving the Carousell app, rather than having to rely on third-party platforms or doing meet-ups to hand over cash. CarouPay will be a digital wallet within the Carousell app. \"More than half of our sellers will end up buying items as well, so maybe it makes sense to have that money in the wallet for purchases\" - Quek tells Tech in Asia.",
                banner_url= "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg",
                time_created= 1532853058,
                rank= 2
            )
        )
    }
    NewsListView(modifier = Modifier, isLoading = true, news = items)
}