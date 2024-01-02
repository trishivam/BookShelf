package com.shivam.bookshelf.network

import com.shivam.bookshelf.data.ImageLinks
import retrofit2.http.GET

interface BookShelfApiService {
    @GET("v1")
suspend fun getBookShelfImageLink(): List<ImageLinks>
}