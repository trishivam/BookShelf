package com.shivam.bookshelf.data

import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    val authors: List<String> = emptyList(),
    val averageRating: Double = 0.0,
    val canonicalVolumeLink: String = "",
    val categories: List<String> = emptyList(),
    val contentVersion: String = "",
    val description: String = "",
    val imageLinks: ImageLinks = ImageLinks("", ""),
    val infoLink: String = "",
    val language: String = "",
    val maturityRating: String = "",
    val pageCount: Int = 0,
    val previewLink: String = "",
    val printType: String = "",
    val publishedDate: String = "",
    val publisher: String = "",
    val ratingsCount: Double = 0.0,
    val subtitle: String = "",
    val title: String = ""
)