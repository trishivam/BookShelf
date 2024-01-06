package com.shivam.bookshelf.ui.theme.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivam.bookshelf.data.Item
import com.shivam.bookshelf.data.VolumeInfo
import com.shivam.bookshelf.network.BookShelfApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val apiService: BookShelfApiService
) : ViewModel() {
    var loading by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    var bookInfo by mutableStateOf(VolumeInfo())

    fun getBook(id :String)
    {
        loading = true
        viewModelScope.launch {
            try {
                val data = apiService.getBooK(id = id)
                bookInfo = data.volumeInfo
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