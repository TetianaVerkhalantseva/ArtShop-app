package com.example.oblig3_verkhalantseva.presentation.artists.mapper

import com.example.oblig3_verkhalantseva.presentation.artists.data.ArtistWithPhotos
import com.example.oblig3_verkhalantseva.presentation.artists.data.ArtistsWithPhotosUI

fun ArtistWithPhotos.toUI() =
    ArtistsWithPhotosUI(
        artist = artist,
        photos = photos,
        mostExpensivePhotosTitle = mostExpensivePhotoName,
        mostExpensivePhotosPrice = mostExpensivePhotoPrice
    )