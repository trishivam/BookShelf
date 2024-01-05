package com.shivam.bookshelf.network

import com.shivam.bookshelf.data.BookSearchResponse
import com.shivam.bookshelf.data.Item
import com.shivam.bookshelf.data.VolumeInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookShelfApiService {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query:String
    ): BookSearchResponse

    @GET("volumes/{id}")
    suspend fun getBooK (@Path ("id") id: String):Item
}