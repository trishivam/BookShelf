package com.shivam.bookshelf.ui.theme.screen.homescreen

import android.net.http.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivam.bookshelf.data.ImageLinks
import com.shivam.bookshelf.network.BookShelfApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

private const val TAG = "HomeViewModel"
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val apiService: BookShelfApiService
) : ViewModel() {

    var loading by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
    var bookShelf by mutableStateOf(listOf<ImageLinks>())

    init {
        getBookShelfImageLinkData()
    }

    private fun getBookShelfImageLinkData(){
        loading = true
        viewModelScope.launch {
            try {
                val data = apiService.getBookShelfImageLink()
                bookShelf = data
            }
            catch (e: UnknownHostException){
                errorMessage = "Check your Internet"
            }
//            catch (e: HttpException){
//                errorMessage = "Something went wrong on the server"
//            }
            catch (e: Exception){
                errorMessage= "Something went wrong"
            }
            loading = false
        }

    }
}