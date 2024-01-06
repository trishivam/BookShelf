package com.shivam.bookshelf.ui.theme.screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.shivam.bookshelf.ui.theme.navigation.Routes
import com.shivam.bookshelf.ui.theme.screens.appBar.BookShelfAppBar
import com.shivam.bookshelf.ui.theme.screens.HomeScreen.SearchResultViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBookScreen(
    viewModel: SearchResultViewModel = hiltViewModel(),
    onImageClicked: (String) -> Unit,

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val gradientColors = listOf(Red, Yellow, Green, Blue)
        var searchQuery by remember { mutableStateOf("") }
//        val brush = remember {
//            Brush.linearGradient(
//                colors = gradientColors
//            )
//        }
        TextField(
            value = searchQuery.trim(),
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search") },
            trailingIcon = { Icon(
                Icons.Filled.Search,
                contentDescription = null,
                modifier = Modifier
                    .height(30.dp)
                    .clickable {
                        viewModel.getSearchBook(searchQuery)
                    }
            ) },
            singleLine = true,
//            textStyle = TextStyle(brush = brush),
            modifier = Modifier
                .padding(top = 10.dp, bottom = 20.dp)
                .fillMaxWidth()
                .shadow(16.dp)
                .background(
                    color = Color.Green,
                    shape = RoundedCornerShape(20.dp)
                ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )

        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.loading){
                CircularProgressIndicator(
                    color = Blue,
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }
            else if (viewModel.errorMessage.isNotEmpty()){
                Text(text = viewModel.errorMessage)
            }
            else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    items(viewModel.bookShelf){ book ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            )
                        ) {
                            AsyncImage(
                                model = book.volumeInfo.imageLinks.thumbnail,
                                contentDescription = "Smallthumb Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(
                                        onClick = { onImageClicked(book.id) }
                                    )
                            )
                        }

                    }
                }
            }
            }
        }
    }

