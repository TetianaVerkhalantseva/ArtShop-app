package com.example.oblig3_verkhalantseva.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.oblig3_verkhalantseva.R

data class Photo(
    val id: Long,
    @StringRes val titleResId: Int,
    @DrawableRes val imageResId: Int,
    val artist: Artist,
    val category: Category,
    val price: Float
)

enum class Category(val id: Int, @DrawableRes val categoryImage: Int, val nameRes: Int) {
    HOBBY(0, R.drawable.hobby, R.string.hobby),
    NATURE(1, R.drawable.nature, R.string.nature),
    ARCHITECTURE(2, R.drawable.architecture, R.string.architecture),
    DOGS(3, R.drawable.dogs, R.string.dogs),
    VARIOUS(4, R.drawable.various, R.string.various)
}