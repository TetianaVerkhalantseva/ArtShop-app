package com.example.oblig3_verkhalantseva.presentation.category.data

import com.example.oblig3_verkhalantseva.data.model.Category
import com.example.oblig3_verkhalantseva.presentation.artists.data.PhotoUI

data class CategoryWithPhotos(
    val category: Category,
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