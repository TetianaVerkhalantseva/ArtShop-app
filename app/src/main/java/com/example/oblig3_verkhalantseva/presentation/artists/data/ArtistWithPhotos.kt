package com.example.oblig3_verkhalantseva.presentation.artists.data

import com.example.oblig3_verkhalantseva.data.model.Artist

data class ArtistWithPhotos(
    val artist: Artist,
    val photos: MutableList<PhotoUI> = mutableListOf(),
    var mostExpensivePhotoPrice: Float = 0F,
    var mostExpensivePhotoName: Int? = null
) {
    fun addPhoto(photo: PhotoUI) {
        photos.add(photo)
        if (mostExpensivePhotoPrice < photo.price) {
            mostExpensivePhotoPrice = photo.price
            mostExpensivePhotoName = photo.titleResId
        }
    }
}