package com.example.oblig3_verkhalantseva.presentation.category.data

import com.example.oblig3_verkhalantseva.data.model.Category
import com.example.oblig3_verkhalantseva.presentation.artists.data.PhotoUI

data class CategoriesWithPhotosUI(
    val category: Category,
    val photos: List<PhotoUI>,
    val mostExpensivePhotosTitle: Int?,
    val mostExpensivePhotosPrice: Float
)