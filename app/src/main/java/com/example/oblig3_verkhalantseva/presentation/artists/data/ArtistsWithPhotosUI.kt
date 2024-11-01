package com.example.oblig3_verkhalantseva.presentation.artists.data

import com.example.oblig3_verkhalantseva.data.model.Artist

data class ArtistsWithPhotosUI(
    val artist: Artist,
    val photos: List<PhotoUI>,
    val mostExpensivePhotosTitle: Int?,
    val mostExpensivePhotosPrice: Float
)