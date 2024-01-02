package com.shivam.bookshelf.data

import kotlinx.serialization.Serializable

@Serializable
data class BookSearchResponse(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)