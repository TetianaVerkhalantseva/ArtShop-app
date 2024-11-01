package com.example.oblig3_verkhalantseva.presentation.artists.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.oblig3_verkhalantseva.data.model.Category

data class PhotoUI(
    val id: Long,
    @StringRes val titleResId: Int,
    @DrawableRes val imageResId: Int,
    val category: Category,
    val price: Float
)