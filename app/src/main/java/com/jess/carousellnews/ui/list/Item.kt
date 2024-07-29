package com.jess.carousellnews.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jess.carousellnews.ui.theme.TimeText
import com.jess.data.remote.dto.NewsItem
import java.util.concurrent.TimeUnit

@Composable
fun Item(
    modifier: Modifier = Modifier,
    item: NewsItem,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2.5f),
                model = item.banner_url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    color = Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    text = item.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    color = Black,
                    text = item.description,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    color = TimeText,
                    fontSize = 14.sp,
                    text = formatTime(item.time_created),
                )
            }
        }
    }
}

@Preview
@Composable
fun ItemPreview() {
    val item = NewsItem(
        id= 121,
        title= "Carousell is launching its own digital wallet to improve payments for its users",
        description= "Due to launch next month in Singapore, CarouPay will allow buyers and sellers to complete transactions without leaving the Carousell app, rather than having to rely on third-party platforms or doing meet-ups to hand over cash. CarouPay will be a digital wallet within the Carousell app. \"More than half of our sellers will end up buying items as well, so maybe it makes sense to have that money in the wallet for purchases\" - Quek tells Tech in Asia.",
        banner_url= "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg",
        time_created= 1532853058,
        rank= 2
    )
    Item(item = item)
}

fun formatTime(timeCreated: Long): String {
    val currentTime = System.currentTimeMillis() / 1000
    val timeDiff = currentTime - timeCreated
    val days = TimeUnit.SECONDS.toDays(timeDiff)
    return when {
        days < 7 -> "$days days ago"
        days < 30 -> "${days / 7} week(s) ago"
        days < 365 -> "${days / 30} month(s) ago"
        else -> "${days / 365} year(s) ago"
    }
}