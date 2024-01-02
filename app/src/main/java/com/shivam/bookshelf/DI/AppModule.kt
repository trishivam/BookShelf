package com.shivam.bookshelf.DI

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.shivam.bookshelf.network.BookShelfApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val contentType = "application/json".toMediaType()
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl("https://www.googleapis.com/books/v1/")
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()

    @Provides
    @Singleton
    fun provideBookShelApiService(retrofit: Retrofit) = retrofit.create(BookShelfApiService::class.java)
}