package com.example.oblig3_verkhalantseva.presentation.category.mapper

import com.example.oblig3_verkhalantseva.presentation.category.data.CategoriesWithPhotosUI
import com.example.oblig3_verkhalantseva.presentation.category.data.CategoryWithPhotos

fun CategoryWithPhotos.toUI() =
    CategoriesWithPhotosUI(
        category = category,
        photos = photos,
        mostExpensivePhotosTitle = mostExpensivePhotoName,
        mostExpensivePhotosPrice = mostExpensivePhotoPrice
    )