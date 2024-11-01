package com.example.oblig3_verkhalantseva.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Artist (
    val id: Int,
    @StringRes val name: Int,
    @DrawableRes val imageResId: Int
)