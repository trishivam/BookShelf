package com.shivam.bookshelf.ui.theme.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.shivam.bookshelf.data.VolumeInfo

@Composable
fun BookDetailScreen(
    viewModel: BookDetailViewModel = hiltViewModel(),
    bookId:String,
) {
    LaunchedEffect(Unit) {
        viewModel.getBook(id = bookId)
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.loading){
                CircularProgressIndicator(
                    color = Color.Blue,
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }
            else if (viewModel.errorMessage.isNotEmpty()){
                Text(text = viewModel.errorMessage)
            }
            else {
                GetBookDetail(viewModel.bookInfo)
            }
        }
    }

}

@Composable
fun GetBookDetail(
    bookInfo:VolumeInfo
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text (
                    text = bookInfo.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                Divider(color = Color.Blue, thickness = 1.dp)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                ) {
                    AsyncImage(
                        model = bookInfo.imageLinks.thumbnail,
                        contentDescription = "Smallthumb Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                    )

                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    colors = CardDefaults.cardColors(containerColor = White),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "${bookInfo.authors} \n\n ${bookInfo.publisher} \n" +
                                    " ${bookInfo.publishedDate}",
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    colors = CardDefaults.cardColors(containerColor = White),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ){
                    Text(
                        text = bookInfo.description,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                }
            }
        }
    }

