package com.example.oblig3_verkhalantseva.presentation.category.mapper

import com.example.oblig3_verkhalantseva.data.model.Photo
import com.example.oblig3_verkhalantseva.presentation.artists.data.PhotoUI

fun Photo.toUI() =
    PhotoUI(
        id = id,
        titleResId = titleResId,
        imageResId = imageResId,
        category = category,
        price = price
    )