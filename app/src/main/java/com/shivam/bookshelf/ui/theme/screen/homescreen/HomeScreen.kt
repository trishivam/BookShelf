package com.shivam.bookshelf.ui.theme.screen.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.shivam.bookshelf.data.ImageLinks


@Composable
fun HomeScreen( viewModel: HomeScreenViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (viewModel.loading){
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
        else if (viewModel.errorMessage.isNotEmpty()){
                Text(text = viewModel.errorMessage)
        }
        else {
            BookShelfList(viewModel.bookShelf.map { it.imageLinks })
        }
    }
}

@Composable
private fun BookShelfList(
    bookShelfList: List<ImageLinks>
) {
    for(bookShelf in bookShelfList) {
        Text(text = bookShelf.smallThumbnail)
        Text(text = bookShelf.thumbnail)
    }
}