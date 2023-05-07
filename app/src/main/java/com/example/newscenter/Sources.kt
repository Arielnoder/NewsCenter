package com.example.newscenter


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import kotlinx.serialization.serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


val json = Json {
    ignoreUnknownKeys = true
}

val client = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }
}

const val BASE_URL = "https://newsapi.org/v2/top-headlines"
const val API_KEY = "289c37d91f57411e99a3c0b2d76778d4" // Replace with your actual API key

@Serializable
data class Article(
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    @SerialName("urlToImage") val urlToImageUrl: String?,
    val publishedAt: String,
    val content: String?
)

@Serializable
data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

@Composable
fun Home(navController: NavHostController) {
    val scrollState = rememberLazyListState()
    val uriHandler = LocalUriHandler.current
    val articles = remember { mutableStateOf(emptyList<Article>()) }

    LaunchedEffect(Unit) {
        val response: NewsApiResponse = withContext(Dispatchers.IO) {
            client.get(BASE_URL) {
                parameter("country", "us")
                parameter("pageSize", 20)
                header("Authorization", "Bearer $API_KEY")
            }
        }
        articles.value = response.articles
    }

    LazyColumn(state = scrollState) {
        itemsIndexed(articles.value) { index, article ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(8.dp, shape = MaterialTheme.shapes.medium)
                    .clickable {
                        uriHandler.openUri(article.url)
                    }
            ) {
                Column {
                    Image(
                        painter = rememberAsyncImagePainter(article.urlToImageUrl),
                        contentDescription = article.title,
                        modifier = Modifier
                            .fillMaxWidth(1F)
                            .height(180.dp)
                            .clip(shape = MaterialTheme.shapes.medium)
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(text = article.title, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text(text = article.description ?: "")
                }
            }
        }
    }
}











        @Composable
        fun SourceOne(navController: NavHostController) {
            val scrollState = rememberLazyListState()
            val articles = List(20) { index -> "Article $index" }

            LazyColumn(state = scrollState) {
                itemsIndexed(articles) { index, _ ->
                    ElevatedCard(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .shadow(8.dp, shape = MaterialTheme.shapes.medium)

                            .clickable {
                                navController.navigate(Screen.SourceOne.route)
                            },


                        ) {
                        Column {

                            Image(
                                painter = rememberAsyncImagePainter(model = "https://picsum.photos/800/400"),
                                contentDescription = "Architecture",
                                modifier = Modifier
                                    .fillMaxWidth(1F)
                                    .height(180.dp)
                                    .clip(shape = MaterialTheme.shapes.medium)


                            )




                            Spacer(Modifier.height(16.dp))
                            Text(text = "Article $index", fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(8.dp))
                            Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                        }
                    }
                }
            }
        }


        @Composable
        fun SourceTwo(navController: NavHostController) {
            val scrollState = rememberLazyListState()
            val articles = List(20) { index -> "Article $index" }

            LazyColumn(state = scrollState) {
                itemsIndexed(articles) { index, _ ->
                    ElevatedCard(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .shadow(8.dp, shape = MaterialTheme.shapes.medium)

                            .clickable {
                                navController.navigate(Screen.SourceOne.route)
                            },


                        ) {
                        Column {

                            Image(
                                painter = rememberAsyncImagePainter(model = "https://picsum.photos/800/400"),
                                contentDescription = "Architecture",
                                modifier = Modifier
                                    .fillMaxWidth(1F)
                                    .height(180.dp)
                                    .clip(shape = MaterialTheme.shapes.medium)


                            )




                            Spacer(Modifier.height(16.dp))
                            Text(text = "Article $index", fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(8.dp))
                            Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                        }
                    }
                }
            }
        }




