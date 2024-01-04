package com.shivam.bookshelf.ui.theme.screens.searchResultScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.shivam.bookshelf.data.Item


@Composable
fun SearchResultScreen(
    viewModel: SearchResultViewModel = hiltViewModel(),
    searchValue:String,
) {
    LaunchedEffect(Unit){
        viewModel.getSearchBook(searchValue)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
            BookShelfList(viewModel.bookShelf)
        }
    }
}

@Composable
private fun BookShelfList(
    bookShelfList: List<Item>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(bookShelfList) { book ->
            AsyncImage(
                model = book.volumeInfo.imageLinks.thumbnail,
                contentDescription = "Smallthumb Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().clickable(
                    onClick = { book }
                )
                )
        }
    }
}