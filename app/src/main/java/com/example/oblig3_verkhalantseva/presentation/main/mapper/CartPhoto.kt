package com.example.oblig3_verkhalantseva.presentation.main.mapper

import com.example.oblig3_verkhalantseva.presentation.main.data.CartPhoto
import com.example.oblig3_verkhalantseva.presentation.selectedPhoto.data.PhotoWithSettingsUI

fun PhotoWithSettingsUI.toCartPhoto() =
    CartPhoto(
        photo = selectedPhoto,
        photoSize = selectedPhotoSize,
        frameType = selectedFrameType,
        frameWidth = selectedFrameWidth,
        totalPrice = totalPrice
    )

fun List<CartPhoto>.countTotalPrice() =
    this.sumOf { it.totalPrice }