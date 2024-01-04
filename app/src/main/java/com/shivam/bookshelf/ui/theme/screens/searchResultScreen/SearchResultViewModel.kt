package com.shivam.bookshelf.ui.theme.screens.searchResultScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivam.bookshelf.data.BookSearchResponse
import com.shivam.bookshelf.data.Item
import com.shivam.bookshelf.data.VolumeInfo
import com.shivam.bookshelf.network.BookShelfApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

private const val TAG = "HomeViewModel"
@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val apiService: BookShelfApiService
) : ViewModel() {

    var loading by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    var bookShelf by mutableStateOf(listOf<Item>())

   fun getSearchBook(query:String){
        loading = true
        viewModelScope.launch {
            try {
                val data = apiService.searchBooks(query)
                bookShelf = data.items
            }
            catch (e: UnknownHostException){
                errorMessage = "Check your Internet"
                e.printStackTrace()
            }
            catch (e: Exception) {
                errorMessage= "Something went wrong"
                e.printStackTrace()
            }
            loading = false
        }
    }
}