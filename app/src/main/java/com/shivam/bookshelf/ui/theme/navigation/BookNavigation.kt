package com.shivam.bookshelf.ui.theme.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shivam.bookshelf.data.Item
import com.shivam.bookshelf.ui.theme.screens.BookDetailScreen
import com.shivam.bookshelf.ui.theme.screens.HomeScreen.SearchBookScreen
import com.shivam.bookshelf.ui.theme.screens.searchResultScreen.SearchResultScreen
import com.shivam.bookshelf.ui.theme.screens.searchResultScreen.SearchResultViewModel

enum class Routes {
    SearchScreen,
    ResultScreen,
    BookDetailScreen
}
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationHost (
    navController: NavHostController = rememberNavController(),
) {
    var canPop by remember { mutableStateOf(false) }

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { controller, _, _ ->
            canPop = controller.previousBackStackEntry != null
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = Routes.valueOf(
        backStackEntry?.destination?.route ?: Routes.SearchScreen.name
    )
    Scaffold (
        topBar = {
            BookShelfAppBar(
                currentScreen = currentScreen,
                canNavigateBack = canPop,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { it ->
        val navController = rememberNavController()
        NavHost (
            navController = navController,
            startDestination = Routes.SearchScreen.name,
            modifier = Modifier.padding(it)
        )
        {
            composable (
                route = Routes.SearchScreen.name
            ) {
                SearchBookScreen {
                    navController.navigate(route = "${Routes.ResultScreen.name}/${it}")
                }
            }
            composable (
                route = "${Routes.ResultScreen.name}/{query}",
                arguments = listOf(navArgument("query") {
                type = NavType.StringType
                }
                )) {entry->
                SearchResultScreen(
                    searchValue = entry.arguments?.getString("query", "")?:"",
                ){
                    navController.navigate(route = "${Routes.BookDetailScreen.name}/${it.id}")
                }
            }

            composable (
                route = "${Routes.BookDetailScreen.name}/{bookId}",
                arguments = listOf(navArgument("bookId"){
                    type = NavType.StringType
                }
            )){entry ->
                BookDetailScreen(
                    bookId = entry.arguments?.getString("bookId","")?:""
                )
            }
        }
    }
}


