package com.example.newscenter


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.newscenter.data.remote.responses.Articles


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController) {


    val scrollState = rememberLazyListState()

    var api = AppModule.provideNewsDataApi()
    val newsList = remember { mutableStateOf(listOf<Articles>()) }
    Log.d("Response", "Response is: ${newsList.value}")


    val uriHandler = LocalUriHandler.current















    LaunchedEffect(key1 = true) {
        if(api.getArticles().isEmpty()){
            api.saveArticles()
        }

        newsList.value = api.getArticles()



    }


    LazyColumn(state = scrollState) {

        itemsIndexed(newsList.value) { index, _ ->
            if (newsList.value[index].image != null) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(8.dp, shape = MaterialTheme.shapes.medium)


                        .clickable {
                            uriHandler.openUri(newsList.value[index].url)
                        },


                    ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

                        Column {

                            Image(

                                painter = rememberAsyncImagePainter(model = newsList.value[index].image),
                                contentDescription = "Architecture",
                                modifier = Modifier
                                    .fillMaxWidth(1F)
                                    .height(180.dp)
                                    .clip(shape = MaterialTheme.shapes.medium)


                            )




                            Spacer(Modifier.height(16.dp))
                            Text(text = newsList.value[index].title, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = newsList.value[index].description,
                                fontWeight = FontWeight.Normal,
                                maxLines = 4

                            )


                        }

                    }
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





