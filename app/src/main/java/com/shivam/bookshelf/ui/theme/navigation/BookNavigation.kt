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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shivam.bookshelf.ui.theme.screens.HomeScreen.SearchBookScreen
import com.shivam.bookshelf.ui.theme.screens.details.BookDetailScreen
import com.shivam.bookshelf.ui.theme.screens.appBar.BookShelfAppBar

enum class Routes {
    ResultScreen,
    BookDetailScreen
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
        backStackEntry?.destination?.route ?: Routes.ResultScreen.name
    )

        val navController = rememberNavController()
        NavHost (
            navController = navController,
            startDestination = Routes.ResultScreen.name,
            modifier = Modifier.padding(8.dp)
        )
        {
//            composable (
//                route = Routes.SearchScreen.name
//            ) {
//                SearchBookScreen {
//                    navController.navigate(route = "${Routes.ResultScreen.name}/${it}")
//                }
//            }

            composable (
                route = Routes.ResultScreen.name
                ) {entry->
                SearchBookScreen(
                ){
                    navController.navigate(route = "${Routes.BookDetailScreen.name}/${it}")
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


