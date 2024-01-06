package com.shivam.bookshelf.ui.theme.screens.appBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shivam.bookshelf.ui.theme.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfAppBar (
    currentScreen: Routes,
    canNavigateBack: Boolean,
    navigateUp:()->Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar (
        title = { Text(text = currentScreen.name)},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer

        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {

                    Icon(imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
            }
        }
    )
}
