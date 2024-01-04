package com.shivam.bookshelf.network

import com.shivam.bookshelf.data.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookShelfApiService {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query:String
    ): BookSearchResponse
}